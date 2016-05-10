package nl.Under_Koen.UnderApi.Scoreboard;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import nl.Under_Koen.UnderApi.UnderApi;

class ScoreboardManager {
	
	private Scoreboard Scoreboard;
	private Objective Objective; 
	private DisplaySlot DisplaySlot;
	private Player Player;
	
	public ScoreboardManager (DisplaySlot displaySlot, Player player) {
		setDisplaySlot(displaySlot);
		setPlayer(player);
		setScoreboard(getPlayer().getScoreboard());
		String name = UUID.randomUUID().toString();
		name = name.substring(0, 16);
		setObjective(getScoreboard().registerNewObjective(name, "dummy"));
		getObjective().setDisplaySlot(displaySlot);
	}
	
	@SuppressWarnings("deprecation")
	public ScoreboardManager (DisplaySlot displaySlot, ScoreboardType type, Player player) {
		setDisplaySlot(displaySlot);
		setPlayer(player);
		setScoreboard(getPlayer().getScoreboard());
		if (type != ScoreboardType.MONEY) {
			String name = UUID.randomUUID().toString();
			name = name.substring(0, 16);
			setObjective((getScoreboard().registerNewObjective(name, type.getType())));	
		} else {
			String name = "$"+type.getCurrency().getName()+"_"+UUID.randomUUID().toString();
			name = name.substring(0, 16);
			setObjective((getScoreboard().registerNewObjective(name, "dummy")));
			for (OfflinePlayer p: Bukkit.getOfflinePlayers()) {
				getObjective().getScore(p).setScore((int) UnderApi.getMoney(p, type.getCurrency()).getMoney());
			}
		}
		getObjective().setDisplaySlot(displaySlot);
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
	 * @return the displaySlot
	 */
	public DisplaySlot getDisplaySlot() {
		return DisplaySlot;
	}

	/**
	 * @param displaySlot the displaySlot to set
	 */
	private void setDisplaySlot(DisplaySlot displaySlot) {
		DisplaySlot = displaySlot;
	}
	
	public void Hide() {
		getPlayer().getScoreboard().clearSlot(getDisplaySlot());
	}
}
