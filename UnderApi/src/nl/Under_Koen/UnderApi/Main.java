package nl.Under_Koen.UnderApi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	public static Main plugin;
	
	public Config FriendData = new Config(this, "Friend.yml", "Data");
	public Config AreaData = new Config(this, "Area.yml", "Data");
	public Config MoneyData = new Config(this, "Money.yml", "Data");
	public Config MoneyConfig = new Config(this, "MoneyConfig.yml");
	public Config Config = new Config(this, "Config.yml");
	
	public Config[] Configs = {FriendData, AreaData, MoneyData, Config, MoneyConfig};
	public Config[] DefaultConfigs = {MoneyConfig, Config};
	
	@Override
    public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
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
		plugin = this;
	}
	
	@Override
    public void onDisable() {
		plugin = null;
	}
	
	public boolean onCommand(CommandSender s, Command cmd,String label, String[] args) {
		if (label.equalsIgnoreCase("Test")) {
			
		}
		return false;
	}
}