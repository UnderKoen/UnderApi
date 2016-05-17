package nl.Under_Koen.UnderApi.Money;

import java.util.ArrayList;
import java.util.List;

public class CurrencyManager {

	private final static List<Currency> currencys = new ArrayList<>();
	
	public static void registerCurrency(Currency currency) {
		for (Currency c : currencys) {
			if (currency.getId() == c.getId()) {
				throw new RuntimeException("Can't have the same id");
			}
		}
		currency.setup();
		currencys.add(currency);
	}
	
	@Deprecated
	public static Currency getCurrency(String name) {
		for (Currency c : currencys) {
			if (c.getName() == name) {
				return c;
			}
		}
		return null;
	}
	
	public static Currency getCurrency(int id) {
		for (Currency c : currencys) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	public static List<Currency> getCurrencys() {
		return currencys;
	}
}
