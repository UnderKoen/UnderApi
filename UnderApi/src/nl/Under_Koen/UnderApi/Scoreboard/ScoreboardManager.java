package nl.Under_Koen.UnderApi.Scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {
	
	private Scoreboard Scoreboard;
	private Objective Objective; 
	
	public ScoreboardManager (DisplaySlot displaySlot) {
		setScoreboard(getManager().getNewScoreboard());
		setObjective(getScoreboard().registerNewObjective("scoreboard", "dummy"));
		getObjective().setDisplaySlot(displaySlot);
	}
	
	public ScoreboardManager (DisplaySlot displaySlot, String objectiveType) {
		setScoreboard(getManager().getNewScoreboard());
		setObjective((getScoreboard().registerNewObjective("scoreboard", objectiveType)));
		getObjective().setDisplaySlot(displaySlot);
	}

	/**
	 * @return the manager
	 */
	protected org.bukkit.scoreboard.ScoreboardManager getManager() {
		return Bukkit.getScoreboardManager();
	}

	/**
	 * @return the scoreboard
	 */
	public Scoreboard getScoreboard() {
		return Scoreboard;
	}

	/**
	 * @param scoreboard the scoreboard to set
	 */
	protected void setScoreboard(Scoreboard scoreboard) {
		Scoreboard = scoreboard;
	}

	/**
	 * @return the objective
	 */
	protected Objective getObjective() {
		return Objective;
	}

	/**
	 * @param objective the objective to set
	 */
	protected void setObjective(Objective objective) {
		Objective = objective;
	}
	
}
