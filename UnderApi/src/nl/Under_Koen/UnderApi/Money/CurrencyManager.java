package nl.Under_Koen.UnderApi.Money;

import java.util.ArrayList;
import java.util.List;

public class CurrencyManager {

	private final static List<Currency> currencys = new ArrayList<>();
	
	public static void registerCurrency(Currency currency) {
		currencys.add(currency);
	}
}
