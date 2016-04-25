package nl.Under_Koen.UnderApi.Money;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import nl.Under_Koen.UnderApi.Main;

public class Money {
	
	private static String pathMoney(OfflinePlayer p, String currency) {
		if (currency.isEmpty() || currency == null) {
			return p.getUniqueId().toString() + ".Default" +".Money";
		}
		return p.getUniqueId().toString() + "." + currency +".Money";
	}
	
	public static void setMoney(OfflinePlayer p, double money, String currency) {
		Main.plugin.MoneyData.getConfig().set(pathMoney(p,currency), money);
		Main.plugin.MoneyData.saveConfig();
		MoneySetEvent event = new MoneySetEvent();
		Bukkit.getServer().getPluginManager().callEvent(event);
	}
	
	public static void addMoney(OfflinePlayer p, double add,String currency) {
		double current = Main.plugin.MoneyData.getConfig().getDouble(pathMoney(p,currency));
		double newMoney = current + add;
		Main.plugin.MoneyData.getConfig().set(pathMoney(p,currency), newMoney);
		Main.plugin.MoneyData.saveConfig();
	}
	
	public static void removeMoney(OfflinePlayer p, double sub, String currency) {
		double current = Main.plugin.MoneyData.getConfig().getDouble(pathMoney(p, currency));
		double newMoney = current - sub;
		Main.plugin.MoneyData.getConfig().set(pathMoney(p, currency), newMoney);
		Main.plugin.MoneyData.saveConfig();
	}
	
	public static double getMoney(OfflinePlayer p, String currency) {
		return Main.plugin.MoneyData.getConfig().getDouble(pathMoney(p, currency));
	}
	
	private static String pathMaxMoney(String currency) {
		if (currency.isEmpty() || currency == null) {
			return currency + ".max-money";
		}
		return "Default.max-money";
	}
	
	public static double getMaxMoney(String currency) {
		return Main.plugin.MoneyConfig.getConfig().getDouble(pathMaxMoney(currency));
	}
	
	public static void setMaxMoney(double max, String currency) {
		Main.plugin.MoneyConfig.getConfig().set(pathMaxMoney(currency), max);
		Main.plugin.MoneyConfig.saveConfig();
	}
	
	private static String pathMinMoney(String currency) {
		if (currency.isEmpty() || currency == null) {
			return currency + ".min-money";
		}
		return "Default.max-money";
	}
	
	public static double getMinMoney(String currency) {
		return Main.plugin.MoneyConfig.getConfig().getDouble(pathMinMoney(currency));
	}
	
	public static void setMinMoney(double min, String currency) {
		Main.plugin.MoneyConfig.getConfig().set(pathMinMoney(currency), min);
		Main.plugin.MoneyConfig.saveConfig();
	}
	
	private static String pathCurrencySymbol(String currency) {
		if (currency.isEmpty() || currency == null) {
			return currency + ".min-money";
		}
		return "Default.max-money";
	}
	
	public static String getCurrencySymbol(String currency) {
		return Main.plugin.MoneyConfig.getConfig().getString(pathCurrencySymbol(currency));
	}
	
	public static void setCurrencySymbol(String currencySymbol, String currency) {
		Main.plugin.MoneyConfig.getConfig().set(pathCurrencySymbol(currency), currencySymbol);
		Main.plugin.MoneyConfig.saveConfig();
	}
}
