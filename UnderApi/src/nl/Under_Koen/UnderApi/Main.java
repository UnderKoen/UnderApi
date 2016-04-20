package nl.Under_Koen.UnderApi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	public static Main plugin;
	
	public Config Friend = new Config(this, "Friend.yml", "Data");
	public Config Area = new Config(this, "Area.yml", "Data");
	public Config Money = new Config(this, "Money.yml", "Data");
	public Config Config = new Config(this, "Config.yml");
	public Config MoneyConfig = new Config(this, "MoneyConfig.yml");
	
	public Config[] Configs = {Friend, Area, Config};
	
	@Override
    public void onEnable() {
		for (Config c : Configs) {
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