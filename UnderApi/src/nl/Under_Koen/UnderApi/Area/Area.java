package nl.Under_Koen.UnderApi.Area;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
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
	private Area (Location spawn, String name, ArrayList<OfflinePlayer> player) {
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
		Main.plugin.AreaConfig.getConfig().set(Name + ".Spawn.X", Spawn.getBlockX());
		Main.plugin.AreaConfig.getConfig().set(Name + ".Spawn.Y", Spawn.getBlockY());
		Main.plugin.AreaConfig.getConfig().set(Name + ".Spawn.Z", Spawn.getBlockZ());
		Main.plugin.AreaConfig.getConfig().set(Name + ".Spawn.World", Spawn.getWorld().getName());
		Main.plugin.AreaConfig.saveConfig();
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
			Main.plugin.AreaConfig.getConfig().set(Name +".Players", PlayersUUID);
			Main.plugin.AreaConfig.saveConfig();
			return;
		}
		for (OfflinePlayer p:Players) {
			PlayersUUID.add(p.getUniqueId().toString());
		}
		Main.plugin.AreaConfig.getConfig().set(Name +".Players", PlayersUUID);
		Main.plugin.AreaConfig.saveConfig();
	}

	/**
	 * @param players the players to add
	 */
	public void addPlayer(Player players) {
		Players.add(players);
		PlayersUUID.add(players.getUniqueId().toString());
		Main.plugin.AreaConfig.getConfig().set(Name + ".Players", PlayersUUID);
		Main.plugin.AreaConfig.saveConfig();
	}
	
	/**
	 * @param players the players to add
	 */
	public void addPlayer(OfflinePlayer players) {
		Players.add(players);
		PlayersUUID.add(players.getUniqueId().toString());
		Main.plugin.AreaConfig.getConfig().set(Name + ".Players", PlayersUUID);
		Main.plugin.AreaConfig.saveConfig();
	}
	
	/**
	 * @param players the players to remove
	 */
	public void removePlayer(Player players) {
		Players.remove(players);
		PlayersUUID.remove(players.getUniqueId().toString());
		Main.plugin.AreaConfig.getConfig().set(Name + ".Players", PlayersUUID);
		Main.plugin.AreaConfig.saveConfig();
	}
	
	/**
	 * @param players the players to remove
	 */
	public void removePlayer(OfflinePlayer players) {
		Players.remove(players);
		PlayersUUID.remove(players.getUniqueId().toString());
		Main.plugin.AreaConfig.getConfig().set(Name + ".Players", PlayersUUID);
		Main.plugin.AreaConfig.saveConfig();
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
	
	/**
	 * @param name name of the area
	 * @return the area
	 */
	public static Area getArea(String name) {
		String test = Main.plugin.AreaConfig.getConfig().getString(name+".Spawn.X");
		if (test == "" || test == null || test.isEmpty()) {
			return null;
		}
		int X = Main.plugin.AreaConfig.getConfig().getInt(name + ".Spawn.X");
		int Y = Main.plugin.AreaConfig.getConfig().getInt(name + ".Spawn.Y");
		int Z = Main.plugin.AreaConfig.getConfig().getInt(name + ".Spawn.Z");
		World World = Bukkit.getWorld(Main.plugin.AreaConfig.getConfig().getString(name + ".Spawn.World"));
		List<String> UUIDplayers = Main.plugin.AreaConfig.getConfig().getStringList(name + ".Players");
		ArrayList<OfflinePlayer> players = new ArrayList<OfflinePlayer>(); 
		for (String p: UUIDplayers) {
			players.add(Bukkit.getOfflinePlayer(UUID.fromString(p)));
		}
		return new Area(new Location(World, X, Y, Z), name, players);
	}
}