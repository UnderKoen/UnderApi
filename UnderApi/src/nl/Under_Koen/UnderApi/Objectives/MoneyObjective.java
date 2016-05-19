package nl.Under_Koen.UnderApi.Objectives;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Objective;

import nl.Under_Koen.UnderApi.UnderApi;
import nl.Under_Koen.UnderApi.Events.Money.MoneyUpdateEvent;

public class MoneyObjective implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMoneyUpdate(MoneyUpdateEvent e) {
		for (Player p: Bukkit.getOnlinePlayers()) {
			for (Objective o : p.getScoreboard().getObjectives()) {
				if (o.getName().contains("$"+e.getCurrency().getId())) {
					o.getScore((OfflinePlayer)e.getPlayer()).setScore((int) UnderApi.getMoney(e.getPlayer(), e.getCurrency()).getMoney());
				}
			}
		}
	}
}
