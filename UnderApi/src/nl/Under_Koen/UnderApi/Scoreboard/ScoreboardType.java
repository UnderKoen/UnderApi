package nl.Under_Koen.UnderApi.Scoreboard;

import nl.Under_Koen.UnderApi.Money.Currency;

public enum ScoreboardType {
	MONEY("money"), 
	HEAL("health"),
	DUMMY("dummy"),
	DEATHS("deathCount"),
	KILLS("totalKillCount"),
	PLAYERKILLS("playerKillCount"),
	XPLEVEL("level"),
	FOOD("food"),
	AIR("air"),
	ARMOR("armor");
	
	private String type;
	private Currency currency;
	
	ScoreboardType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	/**
	 * @param c The currency of the MONEY objetive
	 * 
	 * <h4>only works with MONEY
	 */
	public void setCurrency(Currency c) {
		this.currency = c;
	}
	
	/**
	 * @return The currency of the MONEY objetive
	 * 
	 * <h4>only works with MONEY
	 */
	public Currency getCurrency() {
		return currency;
	}
}
