package nl.Under_Koen.UnderApi.Events;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OfflinePlayerEvent extends Event{

	private OfflinePlayer player;
	
	public OfflinePlayerEvent(OfflinePlayer player) {
		setPlayer(player);
	}

	public OfflinePlayer getPlayer() {
		return player;
	}

	public void setPlayer(OfflinePlayer player) {
		this.player = player;
	}
	
	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
	    return handlers;
	}

	public static HandlerList getHandlerList() {
	    return handlers;
	}
	

}
