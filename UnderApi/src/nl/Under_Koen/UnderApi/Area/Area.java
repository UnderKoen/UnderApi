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
	
	
	/**
	 * @param spawn the location of the spawn
	 * @param name the name of the area
	 */
	public Area (Location spawn, String name) {
		setName(name);
		setPlayers(new ArrayList<OfflinePlayer>());
		setSpawn(spawn);
	}
	
	/**
	 * @param spawn the location of the spawn
	 * @param name the name of the area
	 */
	public Area (Location spawn, String name, ArrayList<OfflinePlayer> player) {
		setName(name);
		setPlayers(player);
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
		Main.plugin.AreaData.getConfig().set(Name + ".Spawn.X", Spawn.getBlockX());
		Main.plugin.AreaData.getConfig().set(Name + ".Spawn.Y", Spawn.getBlockY());
		Main.plugin.AreaData.getConfig().set(Name + ".Spawn.Z", Spawn.getBlockZ());
		Main.plugin.AreaData.getConfig().set(Name + ".Spawn.World", Spawn.getWorld().getName());
		Main.plugin.AreaData.saveConfig();
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
	 * @param players the players to set
	 */
	private void setPlayers(ArrayList<OfflinePlayer> players) {
		Players = players;
		PlayersUUID = new ArrayList<String>();
		if (Players == null || Players.isEmpty()) {
			Main.plugin.AreaData.getConfig().set(Name +".Players", PlayersUUID);
			Main.plugin.AreaData.saveConfig();
			return;
		}
		for (OfflinePlayer p:Players) {
			PlayersUUID.add(p.getUniqueId().toString());
		}
		Main.plugin.AreaData.getConfig().set(Name +".Players", PlayersUUID);
		Main.plugin.AreaData.saveConfig();
	}

	/**
	 * @param players the players to add
	 */
	public void addPlayer(Player players) {
		Players.add(players);
		PlayersUUID.add(players.getUniqueId().toString());
		Main.plugin.AreaData.getConfig().set(Name + ".Players", PlayersUUID);
		Main.plugin.AreaData.saveConfig();
	}
	
	/**
	 * @param players the players to add
	 */
	public void addPlayer(OfflinePlayer players) {
		Players.add(players);
		PlayersUUID.add(players.getUniqueId().toString());
		Main.plugin.AreaData.getConfig().set(Name + ".Players", PlayersUUID);
		Main.plugin.AreaData.saveConfig();
	}
	
	/**
	 * @param players the players to remove
	 */
	public void removePlayer(Player players) {
		Players.remove(players);
		PlayersUUID.remove(players.getUniqueId().toString());
		Main.plugin.AreaData.getConfig().set(Name + ".Players", PlayersUUID);
		Main.plugin.AreaData.saveConfig();
	}
	
	/**
	 * @param players the players to remove
	 */
	public void removePlayer(OfflinePlayer players) {
		Players.remove(players);
		PlayersUUID.remove(players.getUniqueId().toString());
		Main.plugin.AreaData.getConfig().set(Name + ".Players", PlayersUUID);
		Main.plugin.AreaData.saveConfig();
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
	
	/**
	 * @return Area Name + "; " + Location + "; " + Players
	 */
	public String toString() {
		String players = "";
		for (OfflinePlayer p : Players) {
			if (players.isEmpty()) {
				players = players + p.getName();
			} else {
				players = players + ", " + p.getName();
			}
		}
		
		return Name + "; " + Spawn.getBlockX() + ", " + Spawn.getBlockY() + ", " +
		Spawn.getBlockZ() + ", '" + Spawn.getWorld().getName() + "'; ";
	}
}