package nl.Under_Koen.UnderApi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import nl.Under_Koen.UnderApi.Money.Currency;
import nl.Under_Koen.UnderApi.Objectives.MoneyObjective;
import nl.Under_Koen.UnderApi.TabCompletion.TabCompleteHandler;

public class Main extends JavaPlugin implements Listener{
	
	public static Main plugin;
	
	public Config friendData = new Config(this, "Friend.yml", "Data");
	public Config areaData = new Config(this, "Area.yml", "Data");
	public Config moneyData = new Config(this, "Money.yml", "Data");
	public Config tabCompleteCommands = new Config(this, "TabCompleteCommands.yml");
	public Config moneyConfig = new Config(this, "MoneyConfig.yml");
	public Config config = new Config(this, "Config.yml");
	
	public Config[] Configs = {tabCompleteCommands, friendData, areaData, moneyData, config, moneyConfig};
	public Config[] DefaultConfigs = {moneyConfig, config, tabCompleteCommands};
	
	@Override
    public void onEnable() {
		plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new MoneyObjective(), this);
		for (Config c : Configs) {
			for (Config c2 : DefaultConfigs) {
				if (c2 == c) {
					c.saveDefaultConfig();
					c.saveConfig();
				}
			}
			c.getConfig();
			c.saveConfig();
		}
		new Currency("oi");
		TabCompleteHandler.defaultTab(this);
	}
	
	@Override
    public void onDisable() {
		plugin = null;
	}
	
	public boolean onCommand(CommandSender s, Command cmd,String label, String[] args) {
		if (label.equalsIgnoreCase("Test")) {
		}
		if (label.equalsIgnoreCase("Test2")) {
		}
		if (label.equalsIgnoreCase("Test3")) {
		}
		if (label.equalsIgnoreCase("Test4")) {
		}
		return false;
	}
}