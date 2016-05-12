package nl.Under_Koen.UnderApi.Events.Money;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import nl.Under_Koen.UnderApi.Events.OfflinePlayerEvent;
import nl.Under_Koen.UnderApi.Money.Currency;

public class MoneyChangeEvent extends OfflinePlayerEvent implements Cancellable{

	private double oldMoney;
	private double newMoney;
	
	
	private Currency currency;
	
	protected Boolean cancelled;
	
	public MoneyChangeEvent(double oldMoney, double newMoney, Currency currency, OfflinePlayer player) {
		super(player);
		setOldMoney(oldMoney);
		setNewMoney(newMoney);
		this.currency = currency;
		setCancelled(false);
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	public double getOldMoney() {
		return oldMoney;
	}

	public void setOldMoney(double oldMoney) {
		this.oldMoney = oldMoney;
	}

	public double getNewMoney() {
		return newMoney;
	}

	public void setNewMoney(double newMoney) {
		this.newMoney = newMoney;
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
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		if (cancelled) {
			setNewMoney(getOldMoney());
		}
		this.cancelled = cancelled;
		
	}
}
