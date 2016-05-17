package nl.Under_Koen.UnderApi.Area;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface AreaSpawn extends Area {
	
	default public void toSpawn(Player player) {
		player.teleport(getSpawn());
	}
	
	/**
	 * @return the location
	 */
	default public Location getSpawn() {
		return new Location(Bukkit.getWorld(config.getConfig().getString(getId() + ".Spawn.World")),
				config.getConfig().getInt(getId() + ".Spawn.X"), 
				config.getConfig().getInt(getId() + ".Spawn.Y"),
				config.getConfig().getInt(getId() + ".Spawn.Z"));
	}

	/**
	 * @param spawn
	 *            the location to set
	 */
	default public void setSpawn(Location spawn) {
		config.getConfig().set(getId() + ".Spawn.X", spawn.getBlockX());
		config.getConfig().set(getId() + ".Spawn.Y", spawn.getBlockY());
		config.getConfig().set(getId() + ".Spawn.Z", spawn.getBlockZ());
		config.getConfig().set(getId() + ".Spawn.World", spawn.getWorld().getName());
		config.saveConfig();
	}
}
