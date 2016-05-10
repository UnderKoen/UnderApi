package nl.Under_Koen.UnderApi.Events;

import org.bukkit.OfflinePlayer;

import nl.Under_Koen.UnderApi.Money.Currency;

public class MoneyRemoveEvent extends MoneyChangeEvent{

	public MoneyRemoveEvent(double oldMoney, double newMoney, Currency currency, OfflinePlayer offlinePlayer) {
		super(oldMoney, newMoney, currency, offlinePlayer);
	}
	
	public double getRemovedMoney() {
		return super.getOldMoney() - super.getNewMoney();
	}
}
