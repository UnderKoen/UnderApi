package nl.Under_Koen.UnderApi.Scoreboard;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class PlayerListManager extends ScoreboardManager {

	public PlayerListManager(Player p) {
		super(DisplaySlot.PLAYER_LIST, p);
	}
	
	public PlayerListManager(ScoreboardType type, Player p) {
		super(DisplaySlot.PLAYER_LIST, type, p);
	}
	
	@SuppressWarnings("deprecation")
	public void setScore(OfflinePlayer p, int score) {
		getObjective().getScore(p).setScore(score);
	}
	
	@SuppressWarnings("deprecation")
	public int getScore(OfflinePlayer p) {
		return getObjective().getScore(p).getScore();
	}
}