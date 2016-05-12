package nl.Under_Koen.UnderApi.Area;

import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import nl.Under_Koen.UnderApi.Main;

public class Area {
	private Location spawn;
	private String name;
	private ArrayList<OfflinePlayer> players;
	private ArrayList<String> playersUUID;
	
	
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
		return spawn;
	}

	/**
	 * @param spawn the location to set
	 */
	public void setSpawn(Location spawn) {
		this.spawn = spawn;
		Main.plugin.areaData.getConfig().set(name + ".Spawn.X", spawn.getBlockX());
		Main.plugin.areaData.getConfig().set(name + ".Spawn.Y", spawn.getBlockY());
		Main.plugin.areaData.getConfig().set(name + ".Spawn.Z", spawn.getBlockZ());
		Main.plugin.areaData.getConfig().set(name + ".Spawn.World", spawn.getWorld().getName());
		Main.plugin.areaData.saveConfig();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	private void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the players
	 */
	public ArrayList<OfflinePlayer> getPlayers() {
		return players;
	}
	
	/**
	 * @param players the players to set
	 */
	private void setPlayers(ArrayList<OfflinePlayer> players) {
		this.players = players;
		this.playersUUID = new ArrayList<String>();
		if (players == null || players.isEmpty()) {
			Main.plugin.areaData.getConfig().set(name +".Players", playersUUID);
			Main.plugin.areaData.saveConfig();
			return;
		}
		for (OfflinePlayer p:players) {
			playersUUID.add(p.getUniqueId().toString());
		}
		Main.plugin.areaData.getConfig().set(name +".Players", playersUUID);
		Main.plugin.areaData.saveConfig();
	}

	/**
	 * @param players the players to add
	 */
	public void addPlayer(Player player) {
		players.add(player);
		playersUUID.add(player.getUniqueId().toString());
		Main.plugin.areaData.getConfig().set(name + ".Players", playersUUID);
		Main.plugin.areaData.saveConfig();
	}
	
	/**
	 * @param players the players to add
	 */
	public void addPlayer(OfflinePlayer player) {
		players.add(player);
		playersUUID.add(player.getUniqueId().toString());
		Main.plugin.areaData.getConfig().set(name + ".Players", playersUUID);
		Main.plugin.areaData.saveConfig();
	}
	
	/**
	 * @param players the players to remove
	 */
	public void removePlayer(Player player) {
		players.remove(players);
		playersUUID.remove(player.getUniqueId().toString());
		Main.plugin.areaData.getConfig().set(name + ".Players", playersUUID);
		Main.plugin.areaData.saveConfig();
	}
	
	/**
	 * @param players the players to remove
	 */
	public void removePlayer(OfflinePlayer player) {
		players.remove(player);
		playersUUID.remove(player.getUniqueId().toString());
		Main.plugin.areaData.getConfig().set(name + ".Players", playersUUID);
		Main.plugin.areaData.saveConfig();
	}
	
	/**
	 * @param player the player
	 * @return true if the player is in area
	 */
	 public Boolean isInArea(OfflinePlayer player) {
		 for (OfflinePlayer p:players) {
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
		StringBuilder playerBuilder = new StringBuilder();
		for (OfflinePlayer p : players) {
			if (playerBuilder.length() == 0) {
				playerBuilder.append(p.getName());
			} else {
				playerBuilder.append(", ").append(p.getName());
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(name).append("; ").append(spawn.getBlockX()).append(", ").append(spawn.getBlockY()).append(", ")
		.append(spawn.getBlockZ()).append(", '").append(spawn.getWorld().getName()).append("'; ").append(playerBuilder);
		
		return sb.toString();
	}
}