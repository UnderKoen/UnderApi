package nl.Under_Koen.UnderApi.Scoreboard;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class SidebarManager extends ScoreboardManager {

	public SidebarManager(Player p) {
		super(DisplaySlot.SIDEBAR, p);
	}
	
	public SidebarManager(ScoreboardType type, Player p) {
		super(DisplaySlot.SIDEBAR, type, p);
	}
	
	@SuppressWarnings("deprecation")
	public void setScore(OfflinePlayer p, int score) {
		getObjective().getScore(p).setScore(score);
	}
	
	@SuppressWarnings("deprecation")
	public int getScore(OfflinePlayer p) {
		return getObjective().getScore(p).getScore();
	}
	
	public void setDisplayName(String displayName) {
		getObjective().setDisplayName(displayName);
	}
}