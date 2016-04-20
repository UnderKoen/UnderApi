package nl.Under_Koen.UnderApi.Money;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import nl.Under_Koen.UnderApi.Main;

public class Money {
	
	private String pathMoney(OfflinePlayer p) {
		return p.getUniqueId().toString() + ".Money";
	}
	
	public void setMoney(OfflinePlayer p, double money) {
		
		Main.plugin.Money.getConfig().set(pathMoney(p), money);
	}
	
	public void addMoney(Player p, double add) {
		double current = Main.plugin.Money.getConfig().getDouble(pathMoney(p));
		double newMoney = current + add;
		Main.plugin.Money.getConfig().set(pathMoney(p), newMoney);
	}
	
	public double getMoney(Player p) {
		return Main.plugin.Money.getConfig().getDouble(pathMoney(p));
	}
}
