package nl.Under_Koen.UnderApi.Objectives;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import nl.Under_Koen.UnderApi.UnderApi;
import nl.Under_Koen.UnderApi.Events.Money.MoneyUpdateEvent;
import nl.Under_Koen.UnderApi.Money.Currency;

public class MoneyObjective implements Listener, Objective {

	Currency c;
	
	public MoneyObjective(Currency currency) {
		c = currency;
	}
	
	@EventHandler
	public void onMoneyUpdate(MoneyUpdateEvent e) {
		if (c.getId() == e.getCurrency().getId()) {
			update(e.getPlayer(), (int) UnderApi.getMoney(e.getPlayer(), e.getCurrency()).getMoney());
		}
	}
	
	@Override
	public int getSpecialId() {
		return c.getId();
	};
	
	@Override
	public String getName() {
		return "$-$";
	}
	
	@Override
	public int getDefaultScore(OfflinePlayer p) {
		return (int) UnderApi.getMoney(p, c).getMoney();
	}

	private boolean registered = false;
	
	@Override
	public void register() {
		registered = true;
	}

	@Override
	public boolean isRegistered() {
		return registered;
	};
}