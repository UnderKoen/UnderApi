package nl.Under_Koen.UnderApi.Area;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface AreaCorners extends Area {

	default void ignoreY () {
		config.getConfig().set(getId() + ".Corners.IgnoreY", true);
	}
	
	default boolean isYIgnored () {
		return config.getConfig().getBoolean(getId() + ".Corners.IgnoreY");
	}
	
	default public void setFirstCorner(Location corner) {
		config.getConfig().set(getId() + ".Corners.First.X", corner.getBlockX());
		config.getConfig().set(getId() + ".Corners.First.Y", corner.getBlockY());
		config.getConfig().set(getId() + ".Corners.First.Z", corner.getBlockZ());
		config.getConfig().set(getId() + ".Corners.First.World", corner.getWorld().getName());
		config.saveConfig();
	}

	default public void setSecondCorner(Location corner) {
		config.getConfig().set(getId() + ".Corners.Second.X", corner.getBlockX());
		config.getConfig().set(getId() + ".Corners.Second.Y", corner.getBlockY());
		config.getConfig().set(getId() + ".Corners.Second.Z", corner.getBlockZ());
		config.getConfig().set(getId() + ".Corners.Second.World", corner.getWorld().getName());
		config.saveConfig();
	}

	default public Location getFirstCorner() {
		return new Location(Bukkit.getWorld(config.getConfig().getString(getId() + ".Corners.First.World")),
				config.getConfig().getInt(getId() + ".Corners.First.X"),
				config.getConfig().getInt(getId() + ".Corners.First.Y"),
				config.getConfig().getInt(getId() + ".Corners.First.Z"));
	}

	default public Location getSecondCorner() {
		return new Location(Bukkit.getWorld(config.getConfig().getString(getId() + ".Corners.Second.World")),
				config.getConfig().getInt(getId() + ".Corners.Second.X"),
				config.getConfig().getInt(getId() + ".Corners.Second.Y"),
				config.getConfig().getInt(getId() + ".Corners.Second.Z"));
	}

	default public boolean onAreaEnter(Player player) {
		return true;
	}

	default public boolean onAreaLeave(Player player) {
		return true;
	}
}
