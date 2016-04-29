package nl.Under_Koen.UnderApi.Money.Events;

import org.bukkit.OfflinePlayer;

import nl.Under_Koen.UnderApi.Money.Currency;

public class MoneyAddEvent extends MoneyChangeEvent{

	public MoneyAddEvent(double oldMoney, double newMoney, Currency currency, OfflinePlayer player) {
		super(oldMoney, newMoney, currency, player);
	}
}
