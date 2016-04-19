package nl.Under_Koen.UnderApi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import nl.Under_Koen.UnderApi.Area.Area;

public class Main extends JavaPlugin implements Listener{
	
	public static Main plugin;
	
	public Config FriendConfig = new Config(this, "FriendConfig.yml");
	public Config AreaConfig = new Config(this, "AreaConfig.yml");
	
	@Override
    public void onEnable() {
		FriendConfig.getConfig();
		FriendConfig.saveConfig();
		AreaConfig.getConfig();
		AreaConfig.saveConfig();
		plugin = this;
	}
	
	@Override
    public void onDisable() {
		plugin = null;
	}
	
	public boolean onCommand(CommandSender s, Command cmd,String label, String[] args) {
		if (label.equalsIgnoreCase("Test")) {
			new Area(((Player) s).getLocation(), "test");
		}
		if (label.equalsIgnoreCase("Test2")) {
			if (Area.getArea("test") == null) {
				return false;
			}
			s.sendMessage(Area.getArea("test").toString());
		}
		return false;
	}
}