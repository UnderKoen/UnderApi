package nl.Under_Koen.UnderApi.Money;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import nl.Under_Koen.UnderApi.Main;
import nl.Under_Koen.UnderApi.UnderApi;
import nl.Under_Koen.UnderApi.Events.MoneyAddEvent;
import nl.Under_Koen.UnderApi.Events.MoneyChangeEvent;
import nl.Under_Koen.UnderApi.Events.MoneyPayEvent;
import nl.Under_Koen.UnderApi.Events.MoneyRemoveEvent;
import nl.Under_Koen.UnderApi.Events.MoneySetEvent;
import nl.Under_Koen.UnderApi.Events.MoneyUpdateEvent;

public class Money {
	
	public Money (Player player, Currency currency) {
		setPlayer(player);
		setCurrency(currency);
		double money = Main.plugin.MoneyData.getConfig().getDouble(pathMoney());
		Money = money;
		Main.plugin.MoneyData.getConfig().set(pathMoney(), money);
		Main.plugin.MoneyData.saveConfig();
	}
	
	public Player Player;
	
	public Player getPlayer() {
		return Player;
	}

	private void setPlayer(Player player) {
		Player = player;
	}
	
	public Currency Currency;
	
	public Currency getCurrency() {
		return Currency;
	}

	private void setCurrency(Currency currency) {
		Currency = currency;
	}

	private String pathMoney() {
		if (getCurrency() == null) {
			return getPlayer().getUniqueId().toString() + ".Default" +".Money";
		}
		return getPlayer().getUniqueId().toString() + "." + getCurrency().getName() +".Money";
	}
	
	public void setMoney(double money) {
		MoneySetEvent event = new MoneySetEvent(getMoney(), money, getCurrency(), getPlayer());
		Bukkit.getServer().getPluginManager().callEvent(event);
		saveMoney(event.getNewMoney());
	}
	
	public void addMoney(double add) {
		double newMoney = getMoney() + add;
		MoneyAddEvent event = new MoneyAddEvent(getMoney(), newMoney, getCurrency(), getPlayer());
		Bukkit.getServer().getPluginManager().callEvent(event);
		saveMoney(event.getNewMoney());
	}
	
	public void removeMoney(double sub) {
		double newMoney = getMoney() - sub;
		MoneyRemoveEvent event = new MoneyRemoveEvent(getMoney(), newMoney, getCurrency(), getPlayer());
		Bukkit.getServer().getPluginManager().callEvent(event);
		saveMoney(event.getNewMoney());
	}
	
	public void payMoney(Player player2, double payment) throws Exception {
		if (player2 == getPlayer()) {
			throw new Exception("Can't pay yourself");
		}
		double currentP = Main.plugin.MoneyData.getConfig().getDouble(pathMoney());
		double currentP2 = UnderApi.getMoney(player2, getCurrency()).getMoney();
		double newMoneyP = currentP - payment;
		double newMoneyP2 = currentP2 + payment;
		MoneyPayEvent event = new MoneyPayEvent(currentP, newMoneyP, getPlayer(), currentP2, newMoneyP2, player2, getCurrency(), payment);
		Bukkit.getServer().getPluginManager().callEvent(event);
		saveMoney(event.getNewMoney());
		MoneyChangeEvent event2 = new MoneyChangeEvent(event.OldMoneyP2, event.NewMoneyP2, getCurrency(), event.getPlayer2());
		Bukkit.getServer().getPluginManager().callEvent(event2);
		UnderApi.getMoney(player2, getCurrency()).saveMoney(event.getNewMoneyP2());
	}
	
	private double Money;
	
	public double getMoney() {
		return Money;
	}
	
	private void saveMoney(Double money) {
		Money = money;
		Main.plugin.MoneyData.getConfig().set(pathMoney(), money);
		Main.plugin.MoneyData.saveConfig();
		MoneyUpdateEvent event = new MoneyUpdateEvent(getPlayer());
		Bukkit.getServer().getPluginManager().callEvent(event);
	}
}
