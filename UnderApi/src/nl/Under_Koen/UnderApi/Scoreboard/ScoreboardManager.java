package nl.Under_Koen.UnderApi.Scoreboard;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import nl.Under_Koen.UnderApi.Objectives.Objectives;

public abstract class ScoreboardManager {
	
	private Scoreboard scoreboard;
	private Objective objective; 
	private DisplaySlot displaySlot;
	private Player player;
	
	public ScoreboardManager (DisplaySlot displaySlot, Player player) {
		setDisplaySlot(displaySlot);
		setPlayer(player);
		setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		String name = UUID.randomUUID().toString();
		name = name.substring(0, 16);
		setObjective(getScoreboard().registerNewObjective(name, "dummy"));
		getObjective().setDisplaySlot(displaySlot);
		player.setScoreboard(getScoreboard());
	}
	
	public ScoreboardManager (DisplaySlot displaySlot, nl.Under_Koen.UnderApi.Objectives.Objective type, Player player) {
		if (!type.isRegistered()) {
			throw new RuntimeException(type.getName() + " need to be registered");
		}
		setDisplaySlot(displaySlot);
		setPlayer(player);
		setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		String name = UUID.randomUUID().toString();
		name = name.substring(0, 16);
		if (type instanceof Objectives) {
			setObjective((getScoreboard().registerNewObjective(name, type.getType())));
		} else {
			name = type.getName()+"-"+type.getSpecialId()+"-"+UUID.randomUUID().toString();
			name = name.substring(0, 16);
			setObjective((getScoreboard().registerNewObjective(name, type.getType())));
			for (OfflinePlayer p: Bukkit.getOfflinePlayers()) {
				getObjective().getScore(p.getName()).setScore(type.getDefaultScore(p));
			}
		}
		getObjective().setDisplaySlot(displaySlot);
		player.setScoreboard(getScoreboard());
	}

	/**
	 * @return the scoreboard
	 */
	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	/**
	 * @param scoreboard the scoreboard to set
	 */
	protected void setScoreboard(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
	}

	/**
	 * @return the objective
	 */
	protected Objective getObjective() {
		return this.objective;
	}

	/**
	 * @param objective the objective to set
	 */
	protected void setObjective(Objective objective) {
		this.objective = objective;
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
	 * @return the displaySlot
	 */
	public DisplaySlot getDisplaySlot() {
		return displaySlot;
	}

	/**
	 * @param displaySlot the displaySlot to set
	 */
	private void setDisplaySlot(DisplaySlot displaySlot) {
		this.displaySlot = displaySlot;
	}
	
	public void hide() {
		getPlayer().getScoreboard().clearSlot(getDisplaySlot());
	}
}
