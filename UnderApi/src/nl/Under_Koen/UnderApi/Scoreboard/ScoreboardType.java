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
	
	private String Type;
	private Currency Currency;
	
	ScoreboardType(String type) {
		this.Type = type;
	}
	
	public String getType() {
		return Type;
	}
	
	/**
	 * only works with MONEY
	 */
	public void setCurrency(Currency c) {
		this.Currency = c;
	}
	
	/**
	 * only works with MONEY
	 */
	public Currency getCurrency() {
		return Currency;
	}
}
