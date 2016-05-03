package nl.Under_Koen.UnderApi.Friend;

import java.util.ArrayList;
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
	public Friends (Player player) {
		setPlayer(player);
		setFriends(new ArrayList<OfflinePlayer>());
	}
	
	/**
	 * @param player the player
	 * @param pfriends the friends
	 */
	public Friends (Player player, ArrayList<OfflinePlayer> pfriends) {
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
			Main.plugin.FriendData.getConfig().set(Player.getUniqueId()+".Friends", FriendsUUID);
			Main.plugin.FriendData.saveConfig();
			return;
		}
		for (OfflinePlayer p:Friends) {
			FriendsUUID.add(p.getUniqueId().toString());
		}
		Main.plugin.FriendData.getConfig().set(Player.getUniqueId()+".Friends", FriendsUUID);
		Main.plugin.FriendData.saveConfig();
	}
	
	/**
	 * @param friend the friend to add
	 */
	public void addFriend(OfflinePlayer friend) {
		Friends.add(friend);
		FriendsUUID.add(friend.getUniqueId().toString());
		Main.plugin.FriendData.getConfig().set(Player.getUniqueId()+".Friends", FriendsUUID);
		Main.plugin.FriendData.saveConfig();
	}
	
	/**
	 * @param friend the friend to add
	 */
	public void addFriend(Player friend) {
		Friends.add((OfflinePlayer) friend);
		FriendsUUID.add(friend.getUniqueId().toString());
		Main.plugin.FriendData.getConfig().set(Player.getUniqueId()+".Friends", FriendsUUID);
		Main.plugin.FriendData.saveConfig();
	}
	
	/**
	 * @param friend the friend to remove
	 */
	public void removeFriend(OfflinePlayer friend) {
		Friends.remove(friend);
		FriendsUUID.remove(friend.getUniqueId().toString());
		Main.plugin.FriendData.getConfig().set(Player.getUniqueId()+".Friends", FriendsUUID);
		Main.plugin.FriendData.saveConfig();
	}
	
	/**
	 * @param friend the friend to remove
	 */
	public void removeFriend(Player friend) {
		Friends.remove((OfflinePlayer) friend);
		FriendsUUID.remove(friend.getUniqueId().toString());
		Main.plugin.FriendData.getConfig().set(Player.getUniqueId()+".Friends", FriendsUUID);
		Main.plugin.FriendData.saveConfig();
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
}
