package nl.Under_Koen.UnderApi.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class MoneyUpdateEvent extends PlayerEvent{
	
	public MoneyUpdateEvent (Player player) {
		super(player);
	}

	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
	    return handlers;
	}

	public static HandlerList getHandlerList() {
	    return handlers;
	}

}
