package nl.Under_Koen.UnderApi.Objectives;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import nl.Under_Koen.UnderApi.UnderApi;
import nl.Under_Koen.UnderApi.Events.Money.MoneyUpdateEvent;
import nl.Under_Koen.UnderApi.Money.Currency;

public class MoneyObjective implements Listener, Objective {

	Currency c;
	
	MoneyObjective(Currency currency) {
		c = currency;
	}
	
	@EventHandler
	public void onMoneyUpdate(MoneyUpdateEvent e) {
		if (c.getId() == e.getCurrency().getId()) {
			update(e.getPlayer(), (int) UnderApi.getMoney(e.getPlayer(), e.getCurrency()).getMoney(), getFormatterString());
		}
	}
	
	@Override
	public int getSpecialId() {
		return c.getId();
	};
	
	@Override
	public String getPrefixCode() {
		return "$-$";
	};
}