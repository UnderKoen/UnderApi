package nl.Under_Koen.UnderApi.Events.Money;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.HandlerList;

import nl.Under_Koen.UnderApi.Events.OfflinePlayerEvent;
import nl.Under_Koen.UnderApi.Money.Currency;

public class MoneyUpdateEvent extends OfflinePlayerEvent{
	
	public Currency Currency;
	
	public MoneyUpdateEvent (OfflinePlayer offlinePlayer, Currency c) {
		super(offlinePlayer);
		this.Currency = c;
	}

	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
	    return handlers;
	}

	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
	public Currency getCurrency() {
		return Currency;
	}

}
