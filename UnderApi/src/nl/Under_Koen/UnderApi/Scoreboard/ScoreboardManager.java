package nl.Under_Koen.UnderApi.Scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import nl.Under_Koen.UnderApi.UnderApi;

class ScoreboardManager {
	
	private Scoreboard Scoreboard;
	private Objective Objective; 
	
	public ScoreboardManager (DisplaySlot displaySlot) {
		setScoreboard(getManager().getNewScoreboard());
		setObjective(getScoreboard().registerNewObjective("scoreboard", "dummy"));
		getObjective().setDisplaySlot(displaySlot);
	}
	
	@SuppressWarnings("deprecation")
	public ScoreboardManager (DisplaySlot displaySlot, ScoreboardType type) {
		setScoreboard(getManager().getNewScoreboard());
		if (type != ScoreboardType.MONEY) {
			setObjective((getScoreboard().registerNewObjective("scoreboard", type.getType())));	
		} else {
			setObjective((getScoreboard().registerNewObjective("money_"+type.getCurrency().getName(), "dummy")));
			for (OfflinePlayer p: Bukkit.getOfflinePlayers()) {
				getObjective().getScore(p).setScore((int) UnderApi.getMoney(p, type.getCurrency()).getMoney());
			}
		}
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
