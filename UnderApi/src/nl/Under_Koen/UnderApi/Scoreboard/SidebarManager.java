package nl.Under_Koen.UnderApi.Scoreboard;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import nl.Under_Koen.UnderApi.UnderApi;
import nl.Under_Koen.UnderApi.Money.Currency;

public class SidebarManager extends ScoreboardManager {
	
	public SidebarManager() {
		super(DisplaySlot.SIDEBAR);
	}
	
	public void show(Player p) {
		p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		p.setScoreboard(getScoreboard());
	}
	
	public void setDisplayName(String name) {
		getObjective().setDisplayName(name);
	}
	
	private HashMap<Integer, String> Score = new HashMap<Integer, String>();
	
	public String getLine(int line) {
		if (Score.containsKey(line)) {
			return Score.get(line);
		} else {
			return null;
		}
	}
	
	public void setLine(int line, String text) {
		removeLine(line);
		Score.put(line, text);
		String newText = text;
		int i = 0;
		while(getObjective().getScore(newText).getScore() != 0) {
			newText = "§" + i + "§r" + text;
			if (i > 9) {
				return;
			}
			i++;
		}
		getObjective().getScore(newText).setScore(-line);
	}
	
	public void addLine(String text) {
		if (text.length() > 36) {
			return;
		}
		int befor = 0;
		int available = 0;
		for (int i: Score.keySet()) {
			if (i - befor == 2) {
				available = befor+1;
				break;
			}
			befor = i;
		}
		if (available == 0) {
			available = Score.size()+1;
		}
		String newText = text;
		int i = 0;
		while(getObjective().getScore(newText).getScore() != 0) {
			newText = "§" + i + "§r" + text;
			if (i > 9) {
				return;
			}
			i++;
		}
		getObjective().getScore(newText).setScore(-available);
		Score.put(available, newText);
	}
	
	public void removeLine(int line) {
		if (Score.containsKey(line)) {
			getScoreboard().resetScores(Score.get(line));
			Score.remove(line);
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
	
	public void addMoneyLine(Currency currency, Player player, String color) {
		addLine(color + UnderApi.getMoney(player, currency).getMoney() + "");
	}
	
	public void addMoneyLine(Currency currency, Player player) {
		addMoneyLine(currency, player, "");
	}
	
	public void setMoneyLine(Currency currency, Player player, String color, int line) {
		setLine(line, color + UnderApi.getMoney(player, currency).getMoney() + "");
	}
	
	public void setMoneyLine(Currency currency, Player player, int line) {
		setMoneyLine(currency, player, "", line);
	}

	public void addSpace() {
		addLine("");
	}
	
	public void setSpace(int line) {
		setLine(line, "");
	}
}
