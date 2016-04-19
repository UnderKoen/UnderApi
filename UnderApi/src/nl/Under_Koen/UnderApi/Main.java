package nl.Under_Koen.UnderApi;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import nl.Under_Koen.UnderApi.Area.Area;
import nl.Under_Koen.UnderApi.Friend.Friends;

public class Main extends JavaPlugin implements Listener{
	
	private FileConfiguration customConfig = null;
	private File customConfigFile = null;
	
	public void reloadCustomConfig(String config) {
	    customConfigFile = new File(getDataFolder(), config+".yml");
	    customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	}
	
	public FileConfiguration getCustomConfig(String config) {
	    reloadCustomConfig(config);
	    return customConfig;
	}
	
	public void saveCustomConfig(String config) {
		//reloadCustomConfig(config);
	    if (customConfig == null || customConfigFile == null) {
	        return;
	    }
	    try {
	        getCustomConfig(config).save(customConfigFile);
	    } catch (IOException ex) {
	        getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
	    }
	}
	
	public static Main plugin;
	
	@Override
    public void onEnable() {
		getCustomConfig("FriendConfig");
		saveCustomConfig("FriendConfig");
		//getCustomConfig("AreaConfig");
		//saveCustomConfig("AreaConfig");
		plugin = this;
	}
	
	@Override
    public void onDisable() {
		plugin = null;
	}
	
	public boolean onCommand(CommandSender s, Command cmd,String label, String[] args) {
		if (label.equalsIgnoreCase("Test")) {
			//new Area(((Player) s).getLocation(), "test");
			s.sendMessage(Friends.getFriends((Player)s).toString());
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