package nl.Under_Koen.UnderApi.Objectives;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface Objective {
	
	default public void update (Player player, int newScore, String formatterString) {
		for (Player p: Bukkit.getOnlinePlayers()) {
			for (org.bukkit.scoreboard.Objective o : p.getScoreboard().getObjectives()) {
				if (o.getName().contains(formatterString)) {
					o.getScore(player.getName()).setScore(newScore);
				}
			}
		}
	}
	
	default public void update (OfflinePlayer player, int newScore, String formatterString) {
		for (Player p: Bukkit.getOnlinePlayers()) {
			for (org.bukkit.scoreboard.Objective o : p.getScoreboard().getObjectives()) {
				if (o.getName().contains(formatterString)) {
					o.getScore(player.getName()).setScore(newScore);
				}
			}
		}
	}
	
	default public String getType() {
		return "dummy";
	}
	
	default public int getSpecialId() {
		return 0;
	}
	
	default public int getDefaultScore(OfflinePlayer p) {
		return 0;
	}
	
	default public String getPrefixCode() {
		return "";
	}
	
	default public String getFormatterString() {
		String s = String.format("%s-%s-", getPrefixCode(), getSpecialId());
		return s;
	}
}
