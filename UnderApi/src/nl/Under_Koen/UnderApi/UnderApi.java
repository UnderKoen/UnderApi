package nl.Under_Koen.UnderApi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import nl.Under_Koen.UnderApi.Area.Area;
import nl.Under_Koen.UnderApi.Friend.Friends;
import nl.Under_Koen.UnderApi.Money.Currency;
import nl.Under_Koen.UnderApi.Money.Money;

public class UnderApi {

	/**
	 * @param name name of the area
	 * @return the area
	 */
	public static Area getArea(String name) {
		String test = Main.plugin.AreaData.getConfig().getString(name+".Spawn.X");
		if (test == "" || test == null || test.isEmpty()) {
			return null;
		}
		int X = Main.plugin.AreaData.getConfig().getInt(name + ".Spawn.X");
		int Y = Main.plugin.AreaData.getConfig().getInt(name + ".Spawn.Y");
		int Z = Main.plugin.AreaData.getConfig().getInt(name + ".Spawn.Z");
		World World = Bukkit.getWorld(Main.plugin.AreaData.getConfig().getString(name + ".Spawn.World"));
		List<String> UUIDplayers = Main.plugin.AreaData.getConfig().getStringList(name + ".Players");
		ArrayList<OfflinePlayer> players = new ArrayList<OfflinePlayer>(); 
		for (String p: UUIDplayers) {
			players.add(Bukkit.getOfflinePlayer(UUID.fromString(p)));
		}
		return new Area(new Location(World, X, Y, Z), name, players);
	}
	
	/**
	 * @param player the player
	 * @return the friends of the player
	 */
	public static Friends getFriends(Player player) {
		List<String> Ufriends = Main.plugin.FriendData.getConfig().getStringList(player.getUniqueId()+".Friends");
		if (Ufriends.isEmpty() || Ufriends == null) {
			return new Friends(player);
		}
		ArrayList<OfflinePlayer> Pfriends = new ArrayList<OfflinePlayer>();
		for (String friend: Ufriends) {
			Pfriends.add(Bukkit.getOfflinePlayer(UUID.fromString(friend)));
		}
		Friends f = new Friends(player, Pfriends);
		return f;
	}
	
	public static Money getMoney(Player player, Currency currency) {
		return new Money(player, currency);
	}
	
	public static Currency getCurrency(String currency) {
		return new Currency(currency);
	}
}
