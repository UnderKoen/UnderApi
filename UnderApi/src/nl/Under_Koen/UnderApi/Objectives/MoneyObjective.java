package nl.Under_Koen.UnderApi.Objectives;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import nl.Under_Koen.UnderApi.UnderApi;
import nl.Under_Koen.UnderApi.Events.Money.MoneyUpdateEvent;
import nl.Under_Koen.UnderApi.Money.Currency;
import nl.Under_Koen.UnderApi.Money.CurrencyManager;

public class MoneyObjective implements Listener, Objective {

	int c;
	
	public MoneyObjective(Currency currency) {
		c = currency.getId();
	}
	
	public MoneyObjective(int specialId) {
		c = specialId;
	}
	
	@EventHandler
	public void onMoneyUpdate(MoneyUpdateEvent e) {
		if (c == e.getCurrency().getId()) {
			update(e.getPlayer(), (int) UnderApi.getMoney(e.getPlayer(), e.getCurrency()).getMoney());
		}
	}
	
	@Override
	public int getSpecialId() {
		return c;
	};
	
	@Override
	public String getName() {
		return "$-$";
	}
	
	@Override
	public int getDefaultScore(OfflinePlayer p) {
		return (int) UnderApi.getMoney(p, CurrencyManager.getCurrency(c)).getMoney();
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