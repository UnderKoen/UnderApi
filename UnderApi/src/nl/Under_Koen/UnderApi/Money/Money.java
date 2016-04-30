package nl.Under_Koen.UnderApi.Money;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import nl.Under_Koen.UnderApi.Main;
import nl.Under_Koen.UnderApi.Events.MoneyAddEvent;
import nl.Under_Koen.UnderApi.Events.MoneyRemoveEvent;
import nl.Under_Koen.UnderApi.Events.MoneySetEvent;

public class Money {
	
	private static String pathMoney(Player p, Currency currency) {
		if (currency == null) {
			return p.getUniqueId().toString() + ".Default" +".Money";
		}
		return p.getUniqueId().toString() + "." + currency.getName() +".Money";
	}
	
	public static void setMoney(Player p, double money, Currency currency) {
		MoneySetEvent event = new MoneySetEvent(getMoney(p, currency), money, currency, p);
		Bukkit.getServer().getPluginManager().callEvent(event);
		if (!event.isCancelled()) {
			Main.plugin.MoneyData.getConfig().set(pathMoney(p, currency), event.getNewMoney());
			Main.plugin.MoneyData.saveConfig();
		}
	}
	
	public static void addMoney(Player p, double add,Currency currency) {
		double current = Main.plugin.MoneyData.getConfig().getDouble(pathMoney(p,currency));
		double newMoney = current + add;
		MoneyAddEvent event = new MoneyAddEvent(current, newMoney, currency, p);
		Bukkit.getServer().getPluginManager().callEvent(event);
		if (!event.isCancelled()) {
			Main.plugin.MoneyData.getConfig().set(pathMoney(p, currency), event.getNewMoney());
			Main.plugin.MoneyData.saveConfig();
		}
	}
	
	public static void removeMoney(Player p, double sub, Currency currency) {
		double current = Main.plugin.MoneyData.getConfig().getDouble(pathMoney(p, currency));
		double newMoney = current - sub;
		MoneyRemoveEvent event = new MoneyRemoveEvent(current, newMoney, currency, p);
		Bukkit.getServer().getPluginManager().callEvent(event);
		if (!event.isCancelled()) {
			Main.plugin.MoneyData.getConfig().set(pathMoney(p, currency), event.getNewMoney());
			Main.plugin.MoneyData.saveConfig();
		}
	}
	
	public static double getMoney(Player p, Currency currency) {
		return Main.plugin.MoneyData.getConfig().getDouble(pathMoney(p, currency));
	}
}
