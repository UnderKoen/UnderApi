package nl.Under_Koen.UnderApi.Events.Area;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import nl.Under_Koen.UnderApi.Area.Area;

public class PlayerEnterAreaEvent extends PlayerEvent{

	private Area area;
	
	public PlayerEnterAreaEvent(Player who, Area area) {
		super(who);
		setArea(area);
	}

	/**
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
	    return handlers;
	}

	public static HandlerList getHandlerList() {
	    return handlers;
	}

}
