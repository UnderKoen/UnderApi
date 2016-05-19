package nl.Under_Koen.UnderApi.Area;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface AreaPortal extends Area {

	default public void setFirstPortalCorner(Location corner) {
		config.getConfig().set(getId() + ".Portal.Corners.First.X", corner.getBlockX());
		config.getConfig().set(getId() + ".Portal.Corners.First.Y", corner.getBlockY());
		config.getConfig().set(getId() + ".Portal.Corners.First.Z", corner.getBlockZ());
		config.getConfig().set(getId() + ".Portal.Corners.First.World", corner.getWorld().getName());
		config.saveConfig();
	}

	default public void setSecondPortalCorner(Location corner) {
		config.getConfig().set(getId() + ".Portal.Corners.Second.X", corner.getBlockX());
		config.getConfig().set(getId() + ".Portal.Corners.Second.Y", corner.getBlockY());
		config.getConfig().set(getId() + ".Portal.Corners.Second.Z", corner.getBlockZ());
		config.getConfig().set(getId() + ".Portal.Corners.Second.World", corner.getWorld().getName());
		config.saveConfig();
	}

	default public void setPortalSpawn(Location portalSpawn) {
		config.getConfig().set(getId() + ".Portal.Spawn.X", portalSpawn.getBlockX());
		config.getConfig().set(getId() + ".Portal.Spawn.Y", portalSpawn.getBlockY());
		config.getConfig().set(getId() + ".Portal.Spawn.Z", portalSpawn.getBlockZ());
		config.getConfig().set(getId() + ".Portal.Spawn.World", portalSpawn.getWorld().getName());
		config.saveConfig();
	}

	default public void setPortalTimeOut(Long timeOut) {
		config.getConfig().set(getId() + ".Portal.TimeOut", timeOut);
	}

	default public Long getPortalTimeOut() {
		return config.getConfig().getLong(getId() + ".Portal.TimeOut");
	}

	default public Location getFirstPortalCorner() {
		return new Location(Bukkit.getWorld(config.getConfig().getString(getId() + ".Portal.Corners.First.World")),
				config.getConfig().getInt(getId() + ".Portal.Corners.First.X"),
				config.getConfig().getInt(getId() + ".Portal.Corners.First.Y"),
				config.getConfig().getInt(getId() + ".Portal.Corners.First.Z"));
	}

	default public Location getSecondPortalCorner() {
		return new Location(Bukkit.getWorld(config.getConfig().getString(getId() + ".Portal.Corners.Second.World")),
				config.getConfig().getInt(getId() + ".Portal.Corners.Second.X"),
				config.getConfig().getInt(getId() + ".Portal.Corners.Second.Y"),
				config.getConfig().getInt(getId() + ".Portal.Corners.Second.Z"));
	}

	default public Location getPortalSpawn() {
		return new Location(Bukkit.getWorld(config.getConfig().getString(getId() + ".Portal.Spawn.World")),
				config.getConfig().getInt(getId() + ".Portal.Spawn.X"),
				config.getConfig().getInt(getId() + ".Portal.Spawn.Y"),
				config.getConfig().getInt(getId() + ".Portal.Spawn.Z"));
	}

	default public boolean onAreaPortalEnter(Player player) {
		return true;
	}

	default public boolean onAreaPortalSpawn(Player player) {
		return true;
	}
}
