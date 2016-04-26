package nl.Under_Koen.UnderApi.Money;

import org.bukkit.OfflinePlayer;

public class MoneyRemoveEvent extends MoneyChangeEvent{

	public MoneyRemoveEvent(double oldMoney, double newMoney, Currency currency, OfflinePlayer player) {
		super(oldMoney, newMoney, currency, player);
	}
}