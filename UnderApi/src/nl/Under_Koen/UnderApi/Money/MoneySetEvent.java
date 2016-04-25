package nl.Under_Koen.UnderApi.Money;

import org.bukkit.OfflinePlayer;

public class MoneySetEvent extends MoneyChangeEvent{

	public MoneySetEvent(double oldMoney, double newMoney, String currency, OfflinePlayer player) {
		super(oldMoney, newMoney, currency, player);
	}
}
