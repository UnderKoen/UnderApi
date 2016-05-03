package nl.Under_Koen.UnderApi.Events;
import org.bukkit.entity.Player;

import nl.Under_Koen.UnderApi.Money.Currency;

public class MoneySetEvent extends MoneyChangeEvent{

	public MoneySetEvent(double oldMoney, double newMoney, Currency currency, Player player) {
		super(oldMoney, newMoney, currency, player);
	}
	
	public double getSettedMoney() {
		return super.getNewMoney();
	}
}
