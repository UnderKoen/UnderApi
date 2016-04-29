package nl.Under_Koen.UnderApi.Money.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import nl.Under_Koen.UnderApi.Money.Currency;

public class MoneyChangeEvent extends PlayerEvent{

	private double OldMoney;
	private double NewMoney;
	
	
	private Currency Currency;
	
	private Boolean Cancelled;
	
	public MoneyChangeEvent(double oldMoney, double newMoney, Currency currency, Player player) {
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

	public Boolean getCancelled() {
		return Cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		Cancelled = cancelled;
	}
}
