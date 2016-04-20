package nl.Under_Koen.UnderApi.Money;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import nl.Under_Koen.UnderApi.Main;

public class Money {
	
	private static String pathMoney(OfflinePlayer p) {
		return p.getUniqueId().toString() + ".Money";
	}
	
	public static void setMoney(OfflinePlayer p, double money) {
		Main.plugin.MoneyData.getConfig().set(pathMoney(p), money);
		Main.plugin.MoneyData.saveConfig();
	}
	
	public static void addMoney(Player p, double add) {
		double current = Main.plugin.MoneyData.getConfig().getDouble(pathMoney(p));
		double newMoney = current + add;
		Main.plugin.MoneyData.getConfig().set(pathMoney(p), newMoney);
		Main.plugin.MoneyData.saveConfig();
	}
	
	public static void removeMoney(Player p, double sub) {
		double current = Main.plugin.MoneyData.getConfig().getDouble(pathMoney(p));
		double newMoney = current - sub;
		Main.plugin.MoneyData.getConfig().set(pathMoney(p), newMoney);
		Main.plugin.MoneyData.saveConfig();
	}
	
	public static double getMoney(Player p) {
		return Main.plugin.MoneyData.getConfig().getDouble(pathMoney(p));
	}
	
	public static double getMaxMoney() {
		return Main.plugin.MoneyConfig.getConfig().getDouble("max-money");
	}
	
	public static void setMaxMoney(double max) {
		Main.plugin.MoneyConfig.getConfig().set("max-money", max);
		Main.plugin.MoneyConfig.saveConfig();
	}
	
	public static double getMinMoney() {
		return Main.plugin.MoneyConfig.getConfig().getDouble("min-money");
	}
	
	public static void setMinMoney(double min) {
		Main.plugin.MoneyConfig.getConfig().set("min-money", min);
		Main.plugin.MoneyConfig.saveConfig();
	}
	
	public static String getCurrencySymbol() {
		return Main.plugin.MoneyConfig.getConfig().getString("currency-symbol");
	}
	
	public static void setCurrencySymbol(String currencySymbol) {
		Main.plugin.MoneyConfig.getConfig().set("currency-symbol", currencySymbol);
		Main.plugin.MoneyConfig.saveConfig();
	}
}
