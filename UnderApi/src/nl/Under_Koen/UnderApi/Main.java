package nl.Under_Koen.UnderApi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import nl.Under_Koen.UnderApi.Money.Money;

public class Main extends JavaPlugin implements Listener{
	
	public static Main plugin;
	
	public Config FriendData = new Config(this, "Friend.yml", "Data");
	public Config AreaData = new Config(this, "Area.yml", "Data");
	public Config MoneyData = new Config(this, "Money.yml", "Data");
	public Config Config = new Config(this, "Config.yml");
	public Config MoneyConfig = new Config(this, "MoneyConfig.yml");
	
	public Config[] Configs = {FriendData, AreaData, MoneyData, Config, MoneyConfig};
	public Config[] DefaultConfigs = {MoneyConfig};
	
	@Override
    public void onEnable() {
		for (Config c : Configs) {
			for (Config c2 : DefaultConfigs) {
				if (c2 == c) {
					c.saveDefaultConfig();
				}
			}
			c.getConfig();
			c.saveConfig();
		}
		plugin = this;
	}
	
	@Override
    public void onDisable() {
		plugin = null;
	}
	
	public boolean onCommand(CommandSender s, Command cmd,String label, String[] args) {
		if (label.equalsIgnoreCase("Test")) {
			s.sendMessage(Money.getCurrencySymbol() + ": " + Money.getMaxMoney() + ": " + Money.getMinMoney());
		}
		return false;
	}
}