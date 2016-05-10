package nl.Under_Koen.UnderApi.Events;

import org.bukkit.OfflinePlayer;

import nl.Under_Koen.UnderApi.Money.Currency;

public class MoneyAddEvent extends MoneyChangeEvent{

	public MoneyAddEvent(double oldMoney, double newMoney, Currency currency, OfflinePlayer offlinePlayer) {
		super(oldMoney, newMoney, currency, offlinePlayer);
	}
	
	public double getAddedMoney() {
		return getNewMoney() - getOldMoney();
	}
}
