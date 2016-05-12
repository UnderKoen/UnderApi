package nl.Under_Koen.UnderApi.Events.Money;

import org.bukkit.OfflinePlayer;

import nl.Under_Koen.UnderApi.Money.Currency;

public class MoneyPayEvent extends MoneyChangeEvent {
	
	public OfflinePlayer player2;
	
	public Double newMoneyP2;
	public Double oldMoneyP2;
	public Double payment;

	public MoneyPayEvent(double oldMoneyP1, double newMoneyP1, OfflinePlayer offlinePlayer, double oldMoneyP2, double newMoneyP2, OfflinePlayer player22,
			Currency currency, double payment) {
		super(oldMoneyP1, newMoneyP1, currency, offlinePlayer);
		setPlayer2(player22);
		setOldMoneyP2(oldMoneyP2);
		setNewMoneyP2(newMoneyP2);
		setPayment(payment);
	}
	
	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public OfflinePlayer getPlayer2() {
		return player2;
	}

	private void setPlayer2(OfflinePlayer player2) {
		this.player2 = player2;
	}

	public Double getNewMoneyP2() {
		return newMoneyP2;
	}

	public void setNewMoneyP2(Double newMoneyP2) {
		this.newMoneyP2 = newMoneyP2;
	}

	public Double getOldMoneyP2() {
		return oldMoneyP2;
	}

	private void setOldMoneyP2(Double oldMoneyP2) {
		this.oldMoneyP2 = oldMoneyP2;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		if (cancelled) {
			setNewMoney(getOldMoney());
			setNewMoneyP2(getOldMoneyP2());
		}
		this.cancelled = cancelled;
	}
}
