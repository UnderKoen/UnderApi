package nl.Under_Koen.UnderApi.Objectives;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Objective;

import nl.Under_Koen.UnderApi.UnderApi;
import nl.Under_Koen.UnderApi.Events.MoneyUpdateEvent;

public class MoneyObjective implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMoneyUpdate(MoneyUpdateEvent e) {
		for (Player p: Bukkit.getOnlinePlayers()) {
			Objective Money = p.getScoreboard().getObjective("money_"+e.getCurrency().getName());
			if (Money != null) {
				Money.getScore((OfflinePlayer)e.getPlayer()).setScore((int) UnderApi.getMoney(e.getPlayer(), e.getCurrency()).getMoney());
			}
		}
	}
}
