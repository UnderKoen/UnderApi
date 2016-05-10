package nl.Under_Koen.UnderApi.Scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class BelowNameManager extends ScoreboardManager {

	public BelowNameManager() {
		super(DisplaySlot.BELOW_NAME);
	}
	
	public BelowNameManager(ScoreboardType type) {
		super(DisplaySlot.SIDEBAR, type);
	}
	
	public void Show (Player p) {
		p.setScoreboard(getScoreboard());
	}
}