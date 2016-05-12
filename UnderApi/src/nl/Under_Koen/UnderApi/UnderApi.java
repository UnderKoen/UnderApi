package nl.Under_Koen.UnderApi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import nl.Under_Koen.UnderApi.Area.Area;
import nl.Under_Koen.UnderApi.Friend.Friends;
import nl.Under_Koen.UnderApi.Money.Currency;
import nl.Under_Koen.UnderApi.Money.Money;
import nl.Under_Koen.UnderApi.Scoreboard.FakeSidebarManager;

public class UnderApi {

	/**
	 * @param name name of the area
	 * @return the area
	 */
	public static Area getArea(String name) {
		String test = Main.plugin.areaData.getConfig().getString(name+".Spawn.X");
		if (test == "" || test == null || test.isEmpty()) {
			return null;
		}
		int X = Main.plugin.areaData.getConfig().getInt(name + ".Spawn.X");
		int Y = Main.plugin.areaData.getConfig().getInt(name + ".Spawn.Y");
		int Z = Main.plugin.areaData.getConfig().getInt(name + ".Spawn.Z");
		World World = Bukkit.getWorld(Main.plugin.areaData.getConfig().getString(name + ".Spawn.World"));
		List<String> UUIDplayers = Main.plugin.areaData.getConfig().getStringList(name + ".Players");
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
		List<String> Ufriends = Main.plugin.friendData.getConfig().getStringList(player.getUniqueId()+".Friends");
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
	
	public static Money getMoney(OfflinePlayer player, Currency currency) {
		return new Money(player, currency);
	}
	
	public static Currency getCurrency(String currency) {
		return new Currency(currency);
	}
	
	public static FakeSidebarManager getFakeSidebarManager(Player p) {
		Objective ob = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
		FakeSidebarManager sm = new FakeSidebarManager(p);
		if (ob != null) {
			for (String name : p.getScoreboard().getEntries()) {
				if (ob.getScore(name).getScore() != 0) {
					for(int i=0 ; i > 9 ; i++) {
						if (name.contains("§"+i+"§r")) {
							name = name.replace("§"+i+"§r", "");
							break;
						}
					}
					sm.setLine(-ob.getScore(name).getScore(), name);
				}
			}
			sm.setDisplayName(ob.getDisplayName());
		}
		return sm;
	}
}
