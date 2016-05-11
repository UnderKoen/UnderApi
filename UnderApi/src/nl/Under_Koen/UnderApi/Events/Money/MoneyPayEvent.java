package nl.Under_Koen.UnderApi.Events.Money;

import org.bukkit.OfflinePlayer;

import nl.Under_Koen.UnderApi.Money.Currency;

public class MoneyPayEvent extends MoneyChangeEvent {
	
	public OfflinePlayer Player2;
	
	public Double NewMoneyP2;
	public Double OldMoneyP2;
	public Double Payment;

	public MoneyPayEvent(double oldMoneyP1, double newMoneyP1, OfflinePlayer offlinePlayer, double oldMoneyP2, double newMoneyP2, OfflinePlayer player22,
			Currency currency, double payment) {
		super(oldMoneyP1, newMoneyP1, currency, offlinePlayer);
		setPlayer2(player22);
		setOldMoneyP2(oldMoneyP2);
		setNewMoneyP2(newMoneyP2);
		setPayment(payment);
	}
	
	public Double getPayment() {
		return Payment;
	}

	public void setPayment(Double payment) {
		Payment = payment;
	}

	public OfflinePlayer getPlayer2() {
		return Player2;
	}

	private void setPlayer2(OfflinePlayer player2) {
		Player2 = player2;
	}

	public Double getNewMoneyP2() {
		return NewMoneyP2;
	}

	public void setNewMoneyP2(Double newMoneyP2) {
		NewMoneyP2 = newMoneyP2;
	}

	public Double getOldMoneyP2() {
		return OldMoneyP2;
	}

	private void setOldMoneyP2(Double oldMoneyP2) {
		OldMoneyP2 = oldMoneyP2;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		if (cancelled) {
			setNewMoney(getOldMoney());
			setNewMoneyP2(getOldMoneyP2());
		}
		Cancelled = cancelled;
	}
}
