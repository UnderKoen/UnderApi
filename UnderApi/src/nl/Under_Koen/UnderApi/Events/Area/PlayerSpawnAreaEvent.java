package nl.Under_Koen.UnderApi.Events.Area;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerSpawnAreaEvent extends Event{

	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
	    return handlers;
	}

	public static HandlerList getHandlerList() {
	    return handlers;
	}

}
