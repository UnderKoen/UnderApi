package nl.Under_Koen.UnderApi.Money;

import java.util.List;

import nl.Under_Koen.UnderApi.Main;

public class Currency {
	
	private String Name;
	private String Symbol;
	
	private Double MaxMoney;
	private Double MinMoney;
	
	public Currency(String name) {
		setName(name);
		if (!Main.plugin.MoneyData.getConfig().getStringList("Currencys").contains(name)) {
			List<String> list = Main.plugin.MoneyData.getConfig().getStringList("Currencys");
			list.add(Name);
			Main.plugin.MoneyData.getConfig().set("Currencys", list);
		}
		Double Max = Main.plugin.MoneyConfig.getConfig().getDouble(pathMaxMoney());
		if (Max == 0) {
			setMaxMoney(new Currency("").getMaxMoney());
		} else {
			setMaxMoney(Max);
		}
		Double Min = Main.plugin.MoneyConfig.getConfig().getDouble(pathMinMoney());
		if (!Main.plugin.MoneyConfig.getConfig().contains(pathMinMoney())) {
			setMinMoney(new Currency("").getMinMoney());
		} else {
			setMinMoney(Min);
		}
		String symbol = Main.plugin.MoneyConfig.getConfig().getString(pathCurrencySymbol());
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
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	private void setName(String name) {
		if (name == null || name.isEmpty()) {
			Name = "Default";
			return;
		}
		Name = name;
	}
	
	private String pathMaxMoney() {
		return Name + ".max-money";
	}
	
	public double getMaxMoney() {
		return MaxMoney;
	}
	
	public void setMaxMoney(double max) {
		this.MaxMoney = max;
		Main.plugin.MoneyConfig.getConfig().set(pathMaxMoney(), max);
		Main.plugin.MoneyConfig.saveConfig();
	}
	
	private String pathMinMoney() {
		return Name + ".min-money";
	}
	
	public double getMinMoney() {
		return MinMoney;
	}
	
	public void setMinMoney(double min) {
		this.MinMoney = min;
		Main.plugin.MoneyConfig.getConfig().set(pathMinMoney(), min);
		Main.plugin.MoneyConfig.saveConfig();
	}
	
	private String pathCurrencySymbol() {
		return Name + ".currency-symbol";
	}
	
	public String getCurrencySymbol() {
		return Symbol;
	}
	
	public void setCurrencySymbol(String currencySymbol) {
		this.Symbol = currencySymbol;
		Main.plugin.MoneyConfig.getConfig().set(pathCurrencySymbol(), currencySymbol);
		Main.plugin.MoneyConfig.saveConfig();
	}

}
