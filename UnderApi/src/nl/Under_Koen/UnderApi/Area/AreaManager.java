package nl.Under_Koen.UnderApi.Area;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import nl.Under_Koen.UnderApi.Main;
import nl.Under_Koen.UnderApi.Area.AreaPermissions.Type;

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

	@SuppressWarnings("deprecation")
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
				
				//AreaEnter
				if (e.getTo().getX() >= newFirst.getBlockX() && e.getTo().getX() <= newSecond.getBlockX()
						&& e.getTo().getZ() >= newFirst.getBlockZ() && e.getTo().getZ() <= newSecond.getBlockZ()
						&& (e.getTo().getY() >= newFirst.getBlockY() && e.getTo().getY() <= newSecond.getBlockY() || a2.isYIgnored())) {
					if (!a2.isInArea((OfflinePlayer) p)) {
						if (a instanceof AreaPermissions) {
							AreaPermissions Per = (AreaPermissions) a;
							if (!Per.hasPermission(p, Type.ENTER_AREA)) {
								e.setCancelled(true);
								return;
							}
						}
						if (!a2.onAreaEnter(p)) {
							e.setCancelled(true);
							return;
						}
						a2.addPlayer(p);
					}
				//AreaLeave
				} else if (a2.isInArea((OfflinePlayer) p)) {
					if (a instanceof AreaPermissions) {
						AreaPermissions Per = (AreaPermissions) a;
						if (!Per.hasPermission(p, Type.LEAVE_AREA)) {
							e.setCancelled(true);
							return;
						}
					}
					if (!a2.onAreaLeave(p)) {
						e.setCancelled(true);
						return;
					}
					a2.removePlayer(p);
				}
			}
			if (a instanceof AreaPortal) {
				AreaPortal a2 = (AreaPortal) a;
				Location first = a2.getFirstPortalCorner();
				Location second = a2.getSecondPortalCorner();
				Location newFirst = a2.getFirstPortalCorner();
				Location newSecond = a2.getSecondPortalCorner();
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
				//PortalEnter
				if (e.getTo().getX() >= newFirst.getBlockX() && e.getTo().getX() <= newSecond.getBlockX()
						&& e.getTo().getZ() >= newFirst.getBlockZ() && e.getTo().getZ() <= newSecond.getBlockZ()
						&& e.getTo().getY() >= newFirst.getBlockY() && e.getTo().getY() <= newSecond.getBlockY()) {
					if (a instanceof AreaPermissions) {
						AreaPermissions Per = (AreaPermissions) a;
						if (!Per.hasPermission(p, Type.ENTER_PORTAL)) {
							e.setCancelled(true);
							return;
						}
					}
					if (!a2.onAreaPortalEnter(p)) {
						e.setCancelled(true);
						return;
					}
					//PortalSpawn
					if (a instanceof AreaPermissions) {
						AreaPermissions Per = (AreaPermissions) a;
						if (!Per.hasPermission(p, Type.SPAWN_PORTAL)) {
							e.setCancelled(true);
							return;
						}
					}
					BukkitRunnable task = new BukkitRunnable() {
						@Override
						public void run() {
							if (e.getTo().getX() >= newFirst.getX() && e.getTo().getX() <= newSecond.getBlockX()
									&& e.getTo().getZ() >= newFirst.getZ() && e.getTo().getZ() <= newSecond.getBlockZ()
									&& e.getTo().getY() >= newFirst.getY()
									&& e.getTo().getY() <= newSecond.getBlockY()) {
								if (!a2.onAreaPortalSpawn(p)) {
									e.setCancelled(true);
									return;
								}
								p.teleport(a2.getPortalSpawn());
							}
						}
					};
					Main.plugin.getServer().getScheduler().runTaskLater(Main.plugin, task, a2.getPortalTimeOut() * 20l);
				}
			}
		}
	}

	@EventHandler
	public void onTp(PlayerTeleportEvent e) {
		PlayerMoveEvent event = new PlayerMoveEvent(e.getPlayer(), e.getFrom(), e.getTo());
		onMove(event);
		if (event.isCancelled()) {
			e.setCancelled(true);
		}
	}
}
