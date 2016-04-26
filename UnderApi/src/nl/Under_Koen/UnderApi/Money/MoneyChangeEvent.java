package nl.Under_Koen.UnderApi.Money;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MoneyChangeEvent extends Event{

	private double OldMoney;
	private double NewMoney;
	
	private OfflinePlayer Player;
	
	private Currency Currency;
	
	private Boolean Cancelled;
	
	public MoneyChangeEvent(double oldMoney, double newMoney, Currency currency, OfflinePlayer player) {
		setOldMoney(oldMoney);
		setNewMoney(newMoney);
		this.Currency = currency;
		this.Player = player;
		setCancelled(false);
	}

	public OfflinePlayer getPlayer() {
		return Player;
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
