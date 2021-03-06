package nl.Under_Koen.UnderApi.Scoreboard;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import nl.Under_Koen.UnderApi.UnderApi;
import nl.Under_Koen.UnderApi.Money.Currency;

public class FakeSidebarManager extends ScoreboardManager {
	
	public FakeSidebarManager(Player p) {
		super(DisplaySlot.SIDEBAR, p);
	}
	
	public void setDisplayName(String name) {
		getObjective().setDisplayName(name);
	}
	
	private HashMap<Integer, String> lines = new HashMap<Integer, String>();
	
	public String getLine(int line) {
		if (lines.containsKey(line)) {
			return lines.get(line);
		} else {
			return null;
		}
	}
	
	public HashMap<Integer, String> getLines() {
		return lines;
	}
	
	public void setLine(int line, String text) {
		removeLine(line);
		lines.put(line, text);
		String newText = text;
		for(int i=0 ; (getObjective().getScore(newText).getScore() != 0) ; i++) {
			newText = "§" + i + "§r" + text;
			if (i > 9) {
				return;
			}
		}
		getObjective().getScore(newText).setScore(-line);
	}
	
	public void addLine(String text) {
		if (text.length() > 36) {
			return;
		}
		int befor = 0;
		int available = 0;
		for (int i: lines.keySet()) {
			if (i - befor == 2) {
				available = befor+1;
				break;
			}
			befor = i;
		}
		if (available == 0) {
			available = lines.size()+1;
		}
		setLine(available, text);
		getPlayer().setScoreboard(getScoreboard());
	}
	
	public void removeLine(int line) {
		if (lines.containsKey(line)) {
			getScoreboard().resetScores(lines.get(line));
			lines.remove(line);
			getPlayer().setScoreboard(getScoreboard());
		}
	}
	
	public void addCurrencyLine(Currency currency, String color) {
		addLine(color + currency.getName() + ":");
	}
	
	public void addCurrencyLine(Currency currency) {
		addCurrencyLine(currency, "");
	}
	
	public void setCurrencyLine(Currency currency, String color, int line) {
		setLine(line, color + currency.getName() + ":");
	}
	
	public void setCurrencyLine(Currency currency, int line) {
		setCurrencyLine(currency, "", line);
	}
	
	public void addMoneyLine(Currency currency, String color) {
		addLine(color + UnderApi.getMoney(getPlayer(), currency).getMoney() + "");
	}
	
	public void addMoneyLine(Currency currency) {
		addMoneyLine(currency, "");
	}
	
	public void setMoneyLine(Currency currency, String color, int line) {
		setLine(line, color + UnderApi.getMoney(getPlayer(), currency).getMoney() + "");
	}
	
	public void setMoneyLine(Currency currency, int line) {
		setMoneyLine(currency, "", line);
	}

	public void addSpace() {
		addLine(" ");
	}
	
	public void setSpace(int line) {
		setLine(line, " ");
	}
}
