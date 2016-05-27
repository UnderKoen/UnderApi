package nl.Under_Koen.UnderApi.Test;

import nl.Under_Koen.UnderApi.Money.*;

public class CurrencyTest implements Currency {

	@Override
	public int getId() {
		return 666;
	}

	@Override
	public String getName() {
		return "Test";
	}

	@Override
	public void onLoad() {
		setCurrencySymbol("@");
	}

}
