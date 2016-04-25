package nl.Under_Koen.UnderApi.Money;

import org.bukkit.OfflinePlayer;

public class MoneyAddEvent extends MoneyChangeEvent{

	public MoneyAddEvent(double oldMoney, double newMoney, String currency, OfflinePlayer player) {
		super(oldMoney, newMoney, currency, player);
	}
}
