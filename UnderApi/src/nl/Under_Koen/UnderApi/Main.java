package nl.Under_Koen.UnderApi;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import nl.Under_Koen.UnderApi.Area.*;
import nl.Under_Koen.UnderApi.Objectives.*;
import nl.Under_Koen.UnderApi.TabCompletion.*;

public class Main extends JavaPlugin implements Listener{
	
	public static Main plugin;
	
	public Config friendData = new Config(this, "Friend.yml", "Data");
	public Config areaData = new Config(this, "Area.yml", "Data");
	public Config moneyData = new Config(this, "Money.yml", "Data");
	public Config objectiveData = new Config(this, "Objective.yml", "Data");
	public Config tabCompleteCommands = new Config(this, "TabCompleteCommands.yml");
	public Config moneyConfig = new Config(this, "MoneyConfig.yml");
	public Config config = new Config(this, "Config.yml");
	
	public Config[] configs = {tabCompleteCommands, friendData, areaData, moneyData, config, moneyConfig, objectiveData};
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
		TabComplete.addSubCommand("Test", "Test");
		TabCompleteHandler.defaultTab();
		for (String className: objectiveData.getConfig().getStringList("list")) {
			try {
				String[] strings = className.split(":");
				String idInt = strings[1];
				String classPath = strings[0];
				Class<?> objectiveClass = (Class<?>) Class.forName(classPath);
				int id = Integer.parseInt(idInt);
				Object objective = objectiveClass.getDeclaredConstructor(int.class).newInstance(id);
				if (objective instanceof Objective) {
					Objective objective2 = (Objective) objective;
					ObjectiveManager.registerObjective(objective2);
				}
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
    public void onDisable() {
	}
	
	public boolean onCommand(CommandSender s, Command cmd,String label, String[] args) {
		/*if (s instanceof Player) {
			Player p = (Player) s;
			if (label.equalsIgnoreCase("Test")) {
				MoneyObjective ob = new MoneyObjective(CurrencyManager.getCurrency(666));
				ObjectiveManager.registerObjective(ob);
				SidebarManager sb = new SidebarManager(ob, p);
				sb.setDisplayName("money");
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
		}*/
		return false;
	}
}