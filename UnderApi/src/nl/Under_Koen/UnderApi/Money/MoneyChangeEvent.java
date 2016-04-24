package nl.Under_Koen.UnderApi.Money;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MoneyChangeEvent extends Event{

	public MoneyChangeEvent() {
		
	}

	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
	    return handlers;
	}

	public static HandlerList getHandlerList() {
	    return handlers;
	}
}
