package nl.Under_Koen.UnderApi.Friend;

import java.util.ArrayList;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import nl.Under_Koen.UnderApi.Config;
import nl.Under_Koen.UnderApi.Main;

public class Friends {
	
	private Config config = Main.plugin.areaData;
	
	private Player player;
	private ArrayList<OfflinePlayer> friends;
	private ArrayList<String> friendsUUID;
	
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
		return player;
	}

	/**
	 * @param player the player to set
	 */
	private void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the friends
	 */
	public ArrayList<OfflinePlayer> getFriends() {
		return friends;
	}

	/**
	 * @param friends the friends to set
	 */
	public void setFriends(ArrayList<OfflinePlayer> friends) {
		this.friends = friends;
		this.friendsUUID = new ArrayList<String>();
		if (friends == null || friends.isEmpty()) {
			config.getConfig().set(player.getUniqueId()+".Friends", friendsUUID);
			config.saveConfig();
			return;
		}
		for (OfflinePlayer p:friends) {
			friendsUUID.add(p.getUniqueId().toString());
		}
		config.getConfig().set(player.getUniqueId()+".Friends", friendsUUID);
		config.saveConfig();
	}
	
	/**
	 * @param friend the friend to add
	 */
	public void addFriend(OfflinePlayer friend) {
		friends.add(friend);
		friendsUUID.add(friend.getUniqueId().toString());
		config.getConfig().set(player.getUniqueId()+".Friends", friendsUUID);
		config.saveConfig();
	}
	
	/**
	 * @param friend the friend to add
	 */
	public void addFriend(Player friend) {
		friends.add((OfflinePlayer) friend);
		friendsUUID.add(friend.getUniqueId().toString());
		config.getConfig().set(player.getUniqueId()+".Friends", friendsUUID);
		config.saveConfig();
	}
	
	/**
	 * @param friend the friend to remove
	 */
	public void removeFriend(OfflinePlayer friend) {
		friends.remove(friend);
		friendsUUID.remove(friend.getUniqueId().toString());
		config.getConfig().set(player.getUniqueId()+".Friends", friendsUUID);
		config.saveConfig();
	}
	
	/**
	 * @param friend the friend to remove
	 */
	public void removeFriend(Player friend) {
		friends.remove((OfflinePlayer) friend);
		friendsUUID.remove(friend.getUniqueId().toString());
		config.getConfig().set(player.getUniqueId()+".Friends", friendsUUID);
		config.saveConfig();
	}
	
	/**
	 * @param player the player you want to look if in friend list
	 * @return True if player is in friend list
	 */
	public boolean isFriend(Player player) {
		for (OfflinePlayer p:friends) {
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
		StringBuilder sb = new StringBuilder();
		sb.append(player.getName()).append("; ");
		StringBuilder friendBuilder = new StringBuilder();
		for (OfflinePlayer p:friends) {
			friendBuilder.append(p.getName()).append(", ");
		}
		if (friendBuilder.length() != 0) {
			friendBuilder.substring(0, friendBuilder.length()-2);
		}
		
		sb.append(friendBuilder);
		return sb.toString();
	}
}
