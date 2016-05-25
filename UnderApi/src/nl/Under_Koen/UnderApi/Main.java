package nl.Under_Koen.UnderApi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import nl.Under_Koen.UnderApi.Area.AreaManager;
import nl.Under_Koen.UnderApi.Money.CurrencyManager;
import nl.Under_Koen.UnderApi.Objectives.*;
import nl.Under_Koen.UnderApi.Scoreboard.SidebarManager;
import nl.Under_Koen.UnderApi.TabCompletion.TabCompleteHandler;
import nl.Under_Koen.UnderApi.Test.CurrencyTest;
import nl.Under_Koen.UnderApi.Test.Teleport;

public class Main extends JavaPlugin implements Listener{
	
	public static Main plugin;
	
	public Config friendData = new Config(this, "Friend.yml", "Data");
	public Config areaData = new Config(this, "Area.yml", "Data");
	public Config moneyData = new Config(this, "Money.yml", "Data");
	public Config tabCompleteCommands = new Config(this, "TabCompleteCommands.yml");
	public Config moneyConfig = new Config(this, "MoneyConfig.yml");
	public Config config = new Config(this, "Config.yml");
	
	public Config[] configs = {tabCompleteCommands, friendData, areaData, moneyData, config, moneyConfig};
	public Config[] defaultConfigs = {moneyConfig, config, tabCompleteCommands};
	
	@Override
    public void onEnable() {
		plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new AreaManager(), this);
		for (Config c : configs) {
			for (Config c2 : defaultConfigs) {
				if (c2 == c) {
					c.saveDefaultConfig();
					c.saveConfig();
				}
			}
			c.getConfig();
			c.saveConfig();
		}
		TabCompleteHandler.defaultTab();
		CurrencyManager.registerCurrency(new CurrencyTest());
	}
	
	@Override
    public void onDisable() {
		plugin = null;
	}
	
	public boolean onCommand(CommandSender s, Command cmd,String label, String[] args) {
		if (s instanceof Player) {
			Player p = (Player) s;
			if (label.equalsIgnoreCase("Test")) {
				MoneyObjective ob = new MoneyObjective(CurrencyManager.getCurrency(666));
				ObjectiveManager.registerObjective(ob);
				SidebarManager sb = new SidebarManager(ob, p);
				sb.setDisplayName("MONEYS");
			}
			if (label.equalsIgnoreCase("Test2")) {
				UnderApi.getMoney(p, CurrencyManager.getCurrency(666)).addMoney(10);
			}
			if (label.equalsIgnoreCase("Test3")) {
				Teleport ob = new Teleport();
				ObjectiveManager.registerObjective(ob);
				SidebarManager sb = new SidebarManager(ob, p);
				sb.setDisplayName("tp");
			}
			if (label.equalsIgnoreCase("Test4")) {
				UnderApi.getMoney(p, CurrencyManager.getCurrency(666)).setMoney(1090);
			}
		}
		return false;
	}
}