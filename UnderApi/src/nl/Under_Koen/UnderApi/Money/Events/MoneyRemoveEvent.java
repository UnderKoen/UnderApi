package nl.Under_Koen.UnderApi.Money.Events;

import org.bukkit.entity.Player;

import nl.Under_Koen.UnderApi.Money.Currency;

public class MoneyRemoveEvent extends MoneyChangeEvent{

	public MoneyRemoveEvent(double oldMoney, double newMoney, Currency currency, Player player) {
		super(oldMoney, newMoney, currency, player);
	}
}
