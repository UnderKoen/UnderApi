package nl.Under_Koen.UnderApi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import nl.Under_Koen.UnderApi.Objectives.MoneyObjective;
import nl.Under_Koen.UnderApi.Scoreboard.BelowNameManager;
import nl.Under_Koen.UnderApi.Scoreboard.FakeSidebarManager;
import nl.Under_Koen.UnderApi.Scoreboard.PlayerListManager;
import nl.Under_Koen.UnderApi.Scoreboard.ScoreboardType;
import nl.Under_Koen.UnderApi.Scoreboard.SidebarManager;

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
		plugin = this;
	}
	
	@Override
    public void onDisable() {
		plugin = null;
	}
	
	public boolean onCommand(CommandSender s, Command cmd,String label, String[] args) {
		if (label.equalsIgnoreCase("Test")) {
			ScoreboardType type = ScoreboardType.MONEY;
			type.setCurrency(UnderApi.getCurrency("Money"));
			BelowNameManager bm = new BelowNameManager(type, (Player) s);
			bm.setSuffix("[Money]");
			SidebarManager sm = new SidebarManager(type, (Player) s);
			sm.setDisplayName("[Money]");
			new PlayerListManager(type, (Player) s);
		}
		if (label.equalsIgnoreCase("Test2")) {
			UnderApi.getMoney((Player) s, UnderApi.getCurrency("Money")).addMoney(10);
		}
		if (label.equalsIgnoreCase("Test3")) {
			ScoreboardType type = ScoreboardType.HEAL;
			type.setCurrency(UnderApi.getCurrency("Money"));
			BelowNameManager bm = new BelowNameManager(type, (Player) s);
			bm.setSuffix("[Money]");
			SidebarManager sm = new SidebarManager(type, (Player) s);
			sm.setDisplayName("[Money]");
			new PlayerListManager(type, (Player) s);
		}
		if (label.equalsIgnoreCase("Test4")) {
			FakeSidebarManager sm = UnderApi.getFakeSidebarManager((Player)s);
			sm.setLine(0, "LOL");
		}
		return false;
	}
}