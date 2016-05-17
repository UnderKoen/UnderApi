package nl.Under_Koen.UnderApi.Money;

import java.text.NumberFormat;

import nl.Under_Koen.UnderApi.Config;
import nl.Under_Koen.UnderApi.Main;

public interface Currency {
	
	Config config = Main.plugin.moneyConfig;
	
	int getId();
	
	String getName();
	
	default String pathMaxMoney() {
		return getId() + ".max-money";
	}
	
	default long getMaxMoney() {
		return Long.valueOf(config.getConfig().getString(pathMaxMoney()));
	};
	
	default void setMaxMoney(long max) {
		Main.plugin.moneyConfig.getConfig().set(pathMaxMoney(), NumberFormat.getIntegerInstance().format(max).replace(".", ""));
		Main.plugin.moneyConfig.saveConfig();
	}
	
	default String pathMinMoney() {
		return getId() + ".min-money";
	}
	
	default long getMinMoney() {
		return Long.valueOf(config.getConfig().getString(pathMinMoney()));
	}
	
	default void setMinMoney(long min) {
		Main.plugin.moneyConfig.getConfig().set(pathMinMoney(), NumberFormat.getIntegerInstance().format(min).replace(".", ""));
		Main.plugin.moneyConfig.saveConfig();
	}
	
	default String pathCurrencySymbol() {
		return getId() + ".currency-symbol";
	}
	
	default String getCurrencySymbol() {
		return config.getConfig().getString(pathCurrencySymbol());
	}
	
	default void setCurrencySymbol(String currencySymbol) {
		Main.plugin.moneyConfig.getConfig().set(pathCurrencySymbol(), currencySymbol);
		Main.plugin.moneyConfig.saveConfig();
	}
	
	/**
	 * Here you can put all your config stuff
	 * like:
	 * setMaxMoney(1000000);
	 * setMinMoney(0);
	 * setCurrencySymbol("*");
	 */
	void onLoad();
	
	/**
	 * do NOT edit this
	 * use onLoad
	 */
	default void onSetup() {
		config.getConfig().set(getId() + ".name", getName());
		if (!config.getConfig().contains(pathMaxMoney())) {
			setMaxMoney(Long.valueOf(config.getConfig().getString("Default.max-money")));
		}
		if (!config.getConfig().contains(pathMinMoney())) {
			setMinMoney(Long.valueOf(config.getConfig().getLong("Default.min-money")));
		}
		if (!config.getConfig().contains(pathCurrencySymbol())) {
			setCurrencySymbol(config.getConfig().getString("Default.currency-symbol"));
		}
		onLoad();
	}
	
	default void toDefault() {
		config.getConfig().set(getId() + ".name", getName());
		setMaxMoney(Long.valueOf(config.getConfig().getString("Default.max-money")));
		setMinMoney(Long.valueOf(config.getConfig().getLong("Default.min-money")));
		setCurrencySymbol(config.getConfig().getString("Default.currency-symbol"));
	}

}
