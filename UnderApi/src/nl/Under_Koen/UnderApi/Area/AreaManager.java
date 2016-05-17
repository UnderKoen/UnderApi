package nl.Under_Koen.UnderApi.Area;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import nl.Under_Koen.UnderApi.Events.Area.PlayerEnterAreaEvent;
import nl.Under_Koen.UnderApi.Events.Area.PlayerLeaveAreaEvent;

public class AreaManager implements Listener {

	private final static List<Area> areas = new ArrayList<>();

	public static void registerArea(Area area) {
		for (Area a : areas) {
			if (area.getId() == a.getId()) {
				throw new RuntimeException("Can't have the same id");
			}
		}
		area.onSetup();
		areas.add(area);
	}

	@Deprecated
	public static Area getArea(String name) {
		for (Area a : areas) {
			if (a.getName() == name) {
				return a;
			}
		}
		return null;
	}

	public static Area getArea(int id) {
		for (Area a : areas) {
			if (a.getId() == id) {
				return a;
			}
		}
		return null;
	}

	public static List<Area> getAreas() {
		return areas;
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		for (Area a : areas) {
			if (a instanceof AreaCorners) {
				AreaCorners a2 = (AreaCorners) a;
				Location first = a2.getFirstCorner();
				Location second = a2.getSecondCorner();
				Location newFirst = a2.getFirstCorner();
				Location newSecond = a2.getSecondCorner();
				if (first.getBlockX() > second.getBlockX()) {
					newFirst.setX(second.getBlockX());
					newSecond.setX(first.getBlockX());
				}
				if (first.getBlockY() > second.getBlockY()) {
					newFirst.setY(second.getBlockY());
					newSecond.setY(first.getBlockY());
				}
				if (first.getBlockZ() > second.getBlockZ()) {
					newFirst.setZ(second.getBlockZ());
					newSecond.setZ(first.getBlockZ());
				}
				if (p.getLocation().getBlockX() >= newFirst.getBlockX() && p.getLocation().getBlockX() <= newSecond.getBlockX() &&
						p.getLocation().getBlockZ() >= newFirst.getBlockZ() && p.getLocation().getBlockZ() <= newSecond.getBlockZ() &&
						p.getLocation().getBlockY() >= newFirst.getBlockY() && p.getLocation().getBlockY() <= newSecond.getBlockY()) {
					if (!a2.isInArea((OfflinePlayer) p)) {
						a2.addPlayer(p);
						PlayerEnterAreaEvent event = new PlayerEnterAreaEvent(p, a);
						Bukkit.getServer().getPluginManager().callEvent(event);
					}
				} else if (a2.isInArea((OfflinePlayer) p)) {
					a2.removePlayer(p);
					PlayerLeaveAreaEvent event = new PlayerLeaveAreaEvent(p, a);
					Bukkit.getServer().getPluginManager().callEvent(event);
				}
			}
		}
	}
}