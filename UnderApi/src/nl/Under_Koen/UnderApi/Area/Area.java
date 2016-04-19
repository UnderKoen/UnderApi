package nl.Under_Koen.UnderApi.Area;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Area {
	private Location Spawn;
	private String Name;
	private ArrayList<OfflinePlayer> Players;
	
	public Area (Location spawn, String name) {
		setSpawn(spawn);
		setName(name);
		//TODO config
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
	public void setName(String name) {
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
	}
	
	/**
	 * @param players the players to add
	 */
	public void addPlayer(OfflinePlayer players) {
		Players.add(players);
	}
	
	/**
	 * @param players the players to remove
	 */
	public void removePlayer(Player players) {
		Players.remove(players);
	}
	
	/**
	 * @param players the players to remove
	 */
	public void removePlayer(OfflinePlayer players) {
		Players.remove(players);
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