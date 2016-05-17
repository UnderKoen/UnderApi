package nl.Under_Koen.UnderApi.Area;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import nl.Under_Koen.UnderApi.Config;
import nl.Under_Koen.UnderApi.Main;

public interface Area {
	
	//TODO MAKE SOMETHING LIKE CORNERS FOR AREA

	Config config = Main.plugin.areaData;

	int getId();

	String getName();

	/**
	 * @return the location
	 */
	default public Location getSpawn() {
		return new Location(Bukkit.getWorld(config.getConfig().getString(getName() + ".Spawn.World")),
				config.getConfig().getInt(getName() + ".Spawn.X"), 
				config.getConfig().getInt(getName() + ".Spawn.Y"),
				config.getConfig().getInt(getName() + ".Spawn.Z"));
	}

	/**
	 * @param spawn
	 *            the location to set
	 */
	default public void setSpawn(Location spawn) {
		config.getConfig().set(getName() + ".Spawn.X", spawn.getBlockX());
		config.getConfig().set(getName() + ".Spawn.Y", spawn.getBlockY());
		config.getConfig().set(getName() + ".Spawn.Z", spawn.getBlockZ());
		config.getConfig().set(getName() + ".Spawn.World", spawn.getWorld().getName());
		config.saveConfig();
	}

	/**
	 * @return the players
	 */
	default ArrayList<OfflinePlayer> getPlayers() {
		ArrayList<OfflinePlayer> players = new ArrayList<OfflinePlayer>();
		for (String u : config.getConfig().getStringList(getId() + ".Players")) {
			players.add(Bukkit.getPlayer(u));
		}
		return players;
	}

	/**
	 * @param players
	 *            the players to set
	 */
	default void setPlayers(ArrayList<OfflinePlayer> players) {
		ArrayList<String> uuids = new ArrayList<String>();
		for (OfflinePlayer p : players) {
			uuids.add(p.getUniqueId().toString());
		}
		config.getConfig().set(getId() + ".Players", uuids);
		config.saveConfig();
	}

	/**
	 * @param players
	 *            the players to add
	 */
	default void addPlayer(Player player) {
		addPlayer((OfflinePlayer) player);
	}

	/**
	 * @param players
	 *            the players to add
	 */
	default void addPlayer(OfflinePlayer player) {
		ArrayList<OfflinePlayer> players = getPlayers();
		players.add(player);
		setPlayers(players);
	}

	/**
	 * @param players
	 *            the players to remove
	 */
	default void removePlayer(Player player) {
		removePlayer((OfflinePlayer) player);
	}

	/**
	 * @param players
	 *            the players to remove
	 */
	default void removePlayer(OfflinePlayer player) {
		ArrayList<OfflinePlayer> players = getPlayers();
		players.remove(player);
		setPlayers(players);
	}

	/**
	 * @param player
	 *            the player
	 * @return true if the player is in area
	 */
	default Boolean isInArea(OfflinePlayer player) {
		for (OfflinePlayer p : getPlayers()) {
			if (p == player) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param player the player
	 * @return true if the player is in area
	 */
	default Boolean isInArea(Player player) {
		return isInArea((OfflinePlayer) player);
	}

	/**
	 * Here you can put all your config stuff like:
	 * 
	 */
	void onLoad();

	/**
	 * do NOT edit this use onLoad
	 */
	default void onSetup() {
		config.getConfig().set(getId() + ".name", getName());
		onLoad();
	}
}
