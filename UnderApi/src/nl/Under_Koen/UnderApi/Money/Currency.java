package nl.Under_Koen.UnderApi.Money;

import java.text.NumberFormat;
import java.util.List;

import nl.Under_Koen.UnderApi.Main;

public class Currency {
	
	private String name;
	private String symbol;
	
	private Long maxMoney;
	private Long minMoney;
	
	public Currency(String name) {
		setName(name);
		if (!Main.plugin.moneyData.getConfig().getStringList("Currencys").contains(name)) {
			List<String> list = Main.plugin.moneyData.getConfig().getStringList("Currencys");
			list.add(name);
			Main.plugin.moneyData.getConfig().set("Currencys", list);
		}
		if (!Main.plugin.moneyConfig.getConfig().contains(pathMaxMoney())) {
			setMaxMoney(new Currency("").getMaxMoney());
		} else {
			Long max = Long.valueOf(Main.plugin.moneyConfig.getConfig().getString(pathMaxMoney()));
			setMaxMoney(max);
		}
		if (!Main.plugin.moneyConfig.getConfig().contains(pathMinMoney())) {
			setMinMoney(new Currency("").getMinMoney());
		} else {
			Long min = Long.valueOf(Main.plugin.moneyConfig.getConfig().getString(pathMinMoney()));
			setMinMoney(min);
		}
		String symbol = Main.plugin.moneyConfig.getConfig().getString(pathCurrencySymbol());
		if (symbol == null) {
			setCurrencySymbol(new Currency("").getCurrencySymbol());
		} else {
			setCurrencySymbol(symbol);
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	private void setName(String name) {
		if (name == null || name.isEmpty()) {
			this.name = "Default";
			return;
		}
		this.name = name;
	}
	
	private String pathMaxMoney() {
		return name + ".max-money";
	}
	
	public long getMaxMoney() {
		return maxMoney;
	}
	
	public void setMaxMoney(long max) {
		this.maxMoney = max;
		Main.plugin.moneyConfig.getConfig().set(pathMaxMoney(), NumberFormat.getIntegerInstance().format(max).replace(".", ""));
		Main.plugin.moneyConfig.saveConfig();
	}
	
	private String pathMinMoney() {
		return name + ".min-money";
	}
	
	public long getMinMoney() {
		return minMoney;
	}
	
	public void setMinMoney(long min) {
		this.minMoney = min;
		Main.plugin.moneyConfig.getConfig().set(pathMinMoney(), NumberFormat.getIntegerInstance().format(min).replace(".", ""));
		Main.plugin.moneyConfig.saveConfig();
	}
	
	private String pathCurrencySymbol() {
		return name + ".currency-symbol";
	}
	
	public String getCurrencySymbol() {
		return symbol;
	}
	
	public void setCurrencySymbol(String currencySymbol) {
		this.symbol = currencySymbol;
		Main.plugin.moneyConfig.getConfig().set(pathCurrencySymbol(), currencySymbol);
		Main.plugin.moneyConfig.saveConfig();
	}
	
	/**
	 * @return currncy's name
	 */
	public String toString() {
		return name;
	}
}