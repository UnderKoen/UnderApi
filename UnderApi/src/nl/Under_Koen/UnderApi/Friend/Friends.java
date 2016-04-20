package nl.Under_Koen.UnderApi.Friend;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import nl.Under_Koen.UnderApi.Main;

public class Friends {
	private Player Player;
	private ArrayList<OfflinePlayer> Friends;
	private ArrayList<String> FriendsUUID;
	
	/**
	* @param player the player
	*/
	private Friends (Player player) {
		setPlayer(player);
		setFriends(new ArrayList<OfflinePlayer>());
	}
	
	/**
	 * @param player the player
	 * @param pfriends the friends
	 */
	private Friends (Player player, ArrayList<OfflinePlayer> pfriends) {
		setPlayer(player);
		setFriends(pfriends);
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return Player;
	}

	/**
	 * @param player the player to set
	 */
	private void setPlayer(Player player) {
		Player = player;
	}

	/**
	 * @return the friends
	 */
	public ArrayList<OfflinePlayer> getFriends() {
		return Friends;
	}

	/**
	 * @param friends the friends to set
	 */
	public void setFriends(ArrayList<OfflinePlayer> friends) {
		Friends = friends;
		FriendsUUID = new ArrayList<String>();
		if (Friends == null || Friends.isEmpty()) {
			Main.plugin.Friend.getConfig().set(Player.getUniqueId()+".Friends", FriendsUUID);
			Main.plugin.Friend.saveConfig();
			return;
		}
		for (OfflinePlayer p:Friends) {
			FriendsUUID.add(p.getUniqueId().toString());
		}
		Main.plugin.Friend.getConfig().set(Player.getUniqueId()+".Friends", FriendsUUID);
		Main.plugin.Friend.saveConfig();
	}
	
	/**
	 * @param friend the friend to add
	 */
	public void addFriend(OfflinePlayer friend) {
		Friends.add(friend);
		FriendsUUID.add(friend.getUniqueId().toString());
		Main.plugin.Friend.getConfig().set(Player.getUniqueId()+".Friends", FriendsUUID);
		Main.plugin.Friend.saveConfig();
	}
	
	/**
	 * @param friend the friend to add
	 */
	public void addFriend(Player friend) {
		Friends.add((OfflinePlayer) friend);
		FriendsUUID.add(friend.getUniqueId().toString());
		Main.plugin.Friend.getConfig().set(Player.getUniqueId()+".Friends", FriendsUUID);
		Main.plugin.Friend.saveConfig();
	}
	
	/**
	 * @param friend the friend to remove
	 */
	public void removeFriend(OfflinePlayer friend) {
		Friends.remove(friend);
		FriendsUUID.remove(friend.getUniqueId().toString());
		Main.plugin.Friend.getConfig().set(Player.getUniqueId()+".Friends", FriendsUUID);
		Main.plugin.Friend.saveConfig();
	}
	
	/**
	 * @param friend the friend to remove
	 */
	public void removeFriend(Player friend) {
		Friends.remove((OfflinePlayer) friend);
		FriendsUUID.remove(friend.getUniqueId().toString());
		Main.plugin.Friend.getConfig().set(Player.getUniqueId()+".Friends", FriendsUUID);
		Main.plugin.Friend.saveConfig();
	}
	
	/**
	 * @param player the player you want to look if in friend list
	 * @return True if player is in friend list
	 */
	public boolean isFriend(Player player) {
		for (OfflinePlayer p:Friends) {
			if (p == player) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return Player name + "; " + friends
	 */
	public String toString() {
		String friends = "";
		for (OfflinePlayer p:Friends) {
			friends = friends + p.getName() + ", ";
		}
		if (friends.length() != 0) {
			friends = friends.substring(0, friends.length()-2);
		}
		return Player.getName()+"; "+friends;
	}
	
	/**
	 * @param player the player
	 * @return the friends of the player
	 */
	public static Friends getFriends(Player player) {
		List<String> Ufriends = Main.plugin.Friend.getConfig().getStringList(player.getUniqueId()+".Friends");
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
	
	/**
	 * @param player the player
	 * @return true if the player is online
	 */
	 public static Boolean isOnline(OfflinePlayer player) {
		if (player.getPlayer() == null) {
			return false;
		}
		return true;
	}
}
