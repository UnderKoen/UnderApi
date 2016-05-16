package nl.Under_Koen.UnderApi.Money;

import java.text.NumberFormat;

import nl.Under_Koen.UnderApi.Config;
import nl.Under_Koen.UnderApi.Main;

public interface Currency {
	
	Config config = Main.plugin.moneyConfig;
	
	String getName();
	
	void setName(String name);
	
	default String pathMaxMoney() {
		return getName() + ".max-money";
	}
	
	default long getMaxMoney() {
		return config.getConfig().getLong(pathMaxMoney());
	};
	
	default void setMaxMoney(long max) {
		Main.plugin.moneyConfig.getConfig().set(pathMaxMoney(), NumberFormat.getIntegerInstance().format(max).replace(".", ""));
		Main.plugin.moneyConfig.saveConfig();
	}
	
	default String pathMinMoney() {
		return getName() + ".min-money";
	}
	
	default long getMinMoney() {
		return config.getConfig().getLong(pathMinMoney());
	}
	
	default void setMinMoney(long min) {
		Main.plugin.moneyConfig.getConfig().set(pathMinMoney(), NumberFormat.getIntegerInstance().format(min).replace(".", ""));
		Main.plugin.moneyConfig.saveConfig();
	}
	
	default String pathCurrencySymbol() {
		return getName() + ".currency-symbol";
	}
	
	default String getCurrencySymbol() {
		return config.getConfig().getString(pathCurrencySymbol());
	}
	
	default void setCurrencySymbol(String currencySymbol) {
		Main.plugin.moneyConfig.getConfig().set(pathCurrencySymbol(), currencySymbol);
		Main.plugin.moneyConfig.saveConfig();
	}
	
	default void toDefault() {
		setMaxMoney(config.getConfig().getLong("Default.max-money"));
		setMinMoney(config.getConfig().getLong("Default.min-money"));
		setCurrencySymbol(config.getConfig().getString("Default.currency-symbol"));
	}

}
