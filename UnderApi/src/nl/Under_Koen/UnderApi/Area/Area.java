package nl.Under_Koen.UnderApi.Area;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import nl.Under_Koen.UnderApi.Config;
import nl.Under_Koen.UnderApi.Main;

public interface Area {

	Config config = Main.plugin.areaData;

	int getId();

	String getName();

	/**
	 * @return the players
	 */
	default ArrayList<OfflinePlayer> getPlayers() {
		ArrayList<OfflinePlayer> players = new ArrayList<OfflinePlayer>();
		for (String u : config.getConfig().getStringList(getId() + ".Players")) {
			players.add(Bukkit.getOfflinePlayer(UUID.fromString(u)));
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
	 * @param player
	 *            the players to add
	 */
	default void addPlayer(Player player) {
		addPlayer((OfflinePlayer) player);
	}

	/**
	 * @param player
	 *            the players to add
	 */
	default void addPlayer(OfflinePlayer player) {
		if (player == null) { return; }
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
	 * Here you can put all your config stuff
	 */
	void onLoad();

	/**
	 * <h1>do NOT edit this use onLoad
	 */
	default void onSetup() {
		config.getConfig().set(getId() + ".name", getName());
		onLoad();
	}
}
