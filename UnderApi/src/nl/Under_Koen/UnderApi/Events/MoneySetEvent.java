package nl.Under_Koen.UnderApi.Events;
import org.bukkit.OfflinePlayer;
import nl.Under_Koen.UnderApi.Money.Currency;

public class MoneySetEvent extends MoneyChangeEvent{

	public MoneySetEvent(double oldMoney, double newMoney, Currency currency, OfflinePlayer offlinePlayer) {
		super(oldMoney, newMoney, currency, offlinePlayer);
	}
	
	public double getSettedMoney() {
		return super.getNewMoney();
	}
}
