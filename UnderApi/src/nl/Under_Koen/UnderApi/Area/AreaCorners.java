package nl.Under_Koen.UnderApi.Area;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public interface AreaCorners extends Area {
	
	default public void setFirstCorner(Location corner) {
		config.getConfig().set(getName() + ".Corners.First.X", corner.getBlockX());
		config.getConfig().set(getName() + ".Corners.First.Y", corner.getBlockY());
		config.getConfig().set(getName() + ".Corners.First.Z", corner.getBlockZ());
		config.getConfig().set(getName() + ".Corners.First.World", corner.getWorld().getName());
		config.saveConfig();
	}
	
	default public void setSecondCorner(Location corner) {
		config.getConfig().set(getName() + ".Corners.Second.X", corner.getBlockX());
		config.getConfig().set(getName() + ".Corners.Second.Y", corner.getBlockY());
		config.getConfig().set(getName() + ".Corners.Second.Z", corner.getBlockZ());
		config.getConfig().set(getName() + ".Corners.Second.World", corner.getWorld().getName());
		config.saveConfig();
	}
	
	default public Location getFirstCorner() {
		return new Location(Bukkit.getWorld(config.getConfig().getString(getName() + ".Corners.First.World")),
				config.getConfig().getInt(getName() + ".Corners.First.X"), 
				config.getConfig().getInt(getName() + ".Corners.First.Y"),
				config.getConfig().getInt(getName() + ".Corners.First.Z"));
	}
	default public Location getSecondCorner() {
		return new Location(Bukkit.getWorld(config.getConfig().getString(getName() + ".Spawn.World")),
				config.getConfig().getInt(getName() + ".Corners.Second.X"), 
				config.getConfig().getInt(getName() + ".Corners.Second.Y"),
				config.getConfig().getInt(getName() + ".Corners.Second.Z"));
	}
}
