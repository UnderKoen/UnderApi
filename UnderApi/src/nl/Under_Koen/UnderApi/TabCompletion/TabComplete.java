package nl.Under_Koen.UnderApi.TabCompletion;

import java.util.ArrayList;

import org.bukkit.configuration.ConfigurationSection;

import nl.Under_Koen.UnderApi.Main;

public class TabComplete {

	/**
	 * @param command the main command
	 * @param subCommands :
	 * <h1> all the subCommands named like (examples how to looks like in game):</h1>
	 *   -  1.2.3 (/command 1 2 3)
	 *   <br>
	 *   -  1.2.1 (/command 1 2 1)
	 *   <br>
	 *   -  2 (/command 2)
	 *   <br>
	 *   -  3.3.1.2 (/command 3 3 1 2)
	 *   <br><br> you don't need to add 1 because 1.2.3 is already there
	 */
	public static void addSubCommands(String command, String[] subCommands) {
		for (String s: subCommands) {
			Main.plugin.tabCompleteCommands.getConfig().set("Commands."+command+"."+s, true);
			Main.plugin.tabCompleteCommands.saveConfig();
		}
	}
	
	/**
	 * @param command the main command
	 * @param subCommand :
	 * <h1> the subCommand named like (examples how to looks like in game):</h1>
	 *   -  1.2.3 (/command 1 2 3)
	 *   <br>
	 *   -  1.2.1 (/command 1 2 1)
	 *   <br>
	 *   -  2 (/command 2)
	 *   <br>
	 *   -  3.3.1.2 (/command 3 3 1 2)
	 *   <h2>you don't need to add 1 because 1.2.3 is already there
	 */
	public static void addSubCommand(String command, String subCommand) {
		Main.plugin.tabCompleteCommands.getConfig().set("Commands."+command+"."+subCommand, true);
		Main.plugin.tabCompleteCommands.saveConfig();
	}
	
	/**
	 * @param command the main command
	 * @param subCommand :
	 * <h1> the subCommand named like (examples how to looks like in game):</h1>
	 *   -  1.2.3 (/command 1 2 3)
	 *   <br>
	 *   -  1.2.1 (/command 1 2 1)
	 *   <br>
	 *   -  2 (/command 2)
	 *   <br>
	 *   -  3.3.1.2 (/command 3 3 1 2)
	 *  <h2>if you do 1 as subCommand then will 1.2.3 and 1.2.1 also remove
	 */
	public static void removeSubCommand(String command, String subCommand) {
		Main.plugin.tabCompleteCommands.getConfig().set("Commands."+command+"."+subCommand, null);
		Main.plugin.tabCompleteCommands.saveConfig();
	}
	
	/**
	 * @param command the main command
	 * @param subCommand :
	 * <h1> the subCommand named like (examples how to looks like in game):</h1>
	 *   -  1.2.3 (/command 1 2 3)
	 *   <br>
	 *   -  1.2.1 (/command 1 2 1)
	 *   <br>
	 *   -  2 (/command 2)
	 *   <br>
	 *   -  3.3.1.2 (/command 3 3 1 2)
	 *  <h2>if you do 1 as subCommand then will 1.2.3 and 1.2.1 also remove
	 */
	public static void removeSubCommands(String command, String[] subCommands) {
		for (String s : subCommands) {
			Main.plugin.tabCompleteCommands.getConfig().set("Commands."+command+"."+s, null);
		}
		Main.plugin.tabCompleteCommands.saveConfig();
	}
	
	public static void removeCommand(String command) {
		Main.plugin.tabCompleteCommands.getConfig().set("Commands."+command, null);
		Main.plugin.tabCompleteCommands.saveConfig();
	}
	
	/**
	 * @param command the command
	 * @return the subCommands named like:
	 *   <h1>- 1
	 *   <h1>- 1.1
	 *   <h1>- 1.2
	 *   <h1>- 1.2
	 *   <h1>- 1.2.1
	 */
	public static ArrayList<String> getSubCommands(String command) {
		ConfigurationSection commands = Main.plugin.tabCompleteCommands.getConfig().getConfigurationSection("Commands."+command);
		ArrayList<String> newList = new ArrayList<String>();
		for (String s : commands.getKeys(true)) {
			newList.add(s);
		}
		return newList;
	}
}
