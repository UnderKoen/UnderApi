package nl.Under_Koen.UnderApi.Scoreboard;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class BelowNameManager extends ScoreboardManager {

	public BelowNameManager(Player p) {
		super(DisplaySlot.BELOW_NAME, p);
	}
	
	public BelowNameManager(ScoreboardType type, Player p) {
		super(DisplaySlot.BELOW_NAME, type, p);
	}
	
	@SuppressWarnings("deprecation")
	public void setScore(OfflinePlayer p, int score) {
		getObjective().getScore(p).setScore(score);
	}
	
	@SuppressWarnings("deprecation")
	public int getScore(OfflinePlayer p) {
		return getObjective().getScore(p).getScore();
	}
	
	public void setSuffix(String suffix) {
		getObjective().setDisplayName(suffix);
	}
}