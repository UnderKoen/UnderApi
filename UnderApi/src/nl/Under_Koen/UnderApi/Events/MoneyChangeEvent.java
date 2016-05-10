package nl.Under_Koen.UnderApi.Events;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import nl.Under_Koen.UnderApi.Money.Currency;

public class MoneyChangeEvent extends OfflinePlayerEvent implements Cancellable{

	private double OldMoney;
	private double NewMoney;
	
	
	private Currency Currency;
	
	protected Boolean Cancelled;
	
	public MoneyChangeEvent(double oldMoney, double newMoney, Currency currency, OfflinePlayer player) {
		super(player);
		setOldMoney(oldMoney);
		setNewMoney(newMoney);
		this.Currency = currency;
		setCancelled(false);
	}
	
	public Currency getCurrency() {
		return Currency;
	}
	
	public double getOldMoney() {
		return OldMoney;
	}

	public void setOldMoney(double oldMoney) {
		this.OldMoney = oldMoney;
	}

	public double getNewMoney() {
		return NewMoney;
	}

	public void setNewMoney(double newMoney) {
		this.NewMoney = newMoney;
	}
	
	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
	    return handlers;
	}

	public static HandlerList getHandlerList() {
	    return handlers;
	}

	@Override
	public boolean isCancelled() {
		return Cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		if (cancelled) {
			setNewMoney(getOldMoney());
		}
		Cancelled = cancelled;
		
	}
}
