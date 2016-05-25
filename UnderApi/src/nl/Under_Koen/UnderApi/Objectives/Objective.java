package nl.Under_Koen.UnderApi.Objectives;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface Objective {
	
	default public void updateAdd (Player player, int addScore) {
		updateAdd((OfflinePlayer) player, addScore);
	}
	
	default public void updateAdd (OfflinePlayer player, int addScore) {
		for (Player p: Bukkit.getOnlinePlayers()) {
			for (org.bukkit.scoreboard.Objective o : p.getScoreboard().getObjectives()) {
				if (o.getName().contains(getFormatterString())) {
					o.getScore(player.getName()).setScore(o.getScore(player.getName()).getScore() + addScore);
				}
			}
		}
	}
	
	default public void update (Player player, int newScore) {
		update((OfflinePlayer) player, newScore);
	}
	
	default public void update (OfflinePlayer player, int newScore) {
		for (Player p: Bukkit.getOnlinePlayers()) {
			for (org.bukkit.scoreboard.Objective o : p.getScoreboard().getObjectives()) {
				if (o.getName().contains(getFormatterString())) {
					o.getScore(player.getName()).setScore(newScore);
				}
			}
		}
	}
	
	/**
	 * NEED TO SET A VALUE TO TRUE
	 */
	public void register();
	
	/**
	 * RETURNS THE VALUE OF register()
	 */
	public boolean isRegistered();
	
	default public String getType() {
		return "dummy";
	}
	
	default public int getSpecialId() {
		return 0;
	}
	
	default public int getDefaultScore(OfflinePlayer p) {
		return 0;
	}
	
	default public String getName() {
		return "";
	}
	
	default public String getFormatterString() {
		String s = String.format("%s-%s-", getName(), getSpecialId());
		return s;
	}
}
