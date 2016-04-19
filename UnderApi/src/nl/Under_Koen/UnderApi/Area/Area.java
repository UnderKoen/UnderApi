package nl.Under_Koen.UnderApi.Area;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import nl.Under_Koen.UnderApi.Main;

public class Area {
	private Location Spawn;
	private String Name;
	private ArrayList<OfflinePlayer> Players;
	private ArrayList<String> PlayersUUID;
	
	public Area (Location spawn, String name) {
		setName(name);
		setSpawn(spawn);
	}

	/**
	 * @return the location
	 */
	public Location getSpawn() {
		return Spawn;
	}

	/**
	 * @param spawn the location to set
	 */
	public void setSpawn(Location spawn) {
		Spawn = spawn;
		Main.plugin.getCustomConfig("AreaConfig").set(Name + ".Spawn.X", Spawn.getBlockX());
		Main.plugin.getCustomConfig("AreaConfig").set(Name + ".Spawn.Y", Spawn.getBlockY());
		Main.plugin.getCustomConfig("AreaConfig").set(Name + ".Spawn.Z", Spawn.getBlockZ());
		Main.plugin.getCustomConfig("AreaConfig").set(Name + ".Spawn.World", Spawn.getWorld());
		Main.plugin.saveCustomConfig("AreaConfig");
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	private void setName(String name) {
		Name = name;
	}

	/**
	 * @return the players
	 */
	public ArrayList<OfflinePlayer> getPlayers() {
		return Players;
	}

	/**
	 * @param players the players to add
	 */
	public void addPlayer(Player players) {
		Players.add(players);
		PlayersUUID.add(players.getUniqueId().toString());
		Main.plugin.getCustomConfig("AreaConfig").set(Name + ".Players", PlayersUUID);
		Main.plugin.saveCustomConfig("AreaConfig");
	}
	
	/**
	 * @param players the players to add
	 */
	public void addPlayer(OfflinePlayer players) {
		Players.add(players);
		PlayersUUID.add(players.getUniqueId().toString());
		Main.plugin.getCustomConfig("AreaConfig").set(Name + ".Players", PlayersUUID);
		Main.plugin.saveCustomConfig("AreaConfig");
	}
	
	/**
	 * @param players the players to remove
	 */
	public void removePlayer(Player players) {
		Players.remove(players);
		PlayersUUID.remove(players.getUniqueId().toString());
		Main.plugin.getCustomConfig("AreaConfig").set(Name + ".Players", PlayersUUID);
		Main.plugin.saveCustomConfig("AreaConfig");
	}
	
	/**
	 * @param players the players to remove
	 */
	public void removePlayer(OfflinePlayer players) {
		Players.remove(players);
		PlayersUUID.remove(players.getUniqueId().toString());
		Main.plugin.getCustomConfig("AreaConfig").set(Name + ".Players", PlayersUUID);
		Main.plugin.saveCustomConfig("AreaConfig");
	}
	
	/**
	 * @param player the player
	 * @return true if the player is in area
	 */
	 public Boolean isInArea(OfflinePlayer player) {
		 for (OfflinePlayer p:Players) {
				if (p == player) {
					return true;
				}
			}
			return false;
	}
}