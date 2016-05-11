package nl.Under_Koen.UnderApi.TabCompletion;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;

import nl.Under_Koen.UnderApi.Main;

public class TabCompleteHandler implements TabCompleter{

	@Override
	public List<String> onTabComplete(CommandSender s, Command cmd, String alias, String[] args) {
		ConfigurationSection commands = Main.plugin.TabCompleteCommands.getConfig().getConfigurationSection("Commands");
		for (String cmdName : commands.getKeys(false)) {
			ConfigurationSection subcommands = Main.plugin.TabCompleteCommands.getConfig().getConfigurationSection("Commands."+cmdName);
			if (cmd.getLabel().equalsIgnoreCase(cmdName)) {
				String beforeArg = null;
				if (!(args.length <= 1)) {
					beforeArg = args[args.length-2];
				}
				if (beforeArg == null) {
					List<String> list = new ArrayList<>();
					for (String subCommands : commands.getConfigurationSection(cmdName).getKeys(false)) {
						list.add(subCommands);
					}
					return list;
				} else {
					String lastArg = args[args.length-1]; 
					String path = "";
					for (String arg: args) {
						if (arg != lastArg) {
							path = path + "." + arg;
						}
					}
					if (Main.plugin.TabCompleteCommands.getConfig().contains(subcommands.getCurrentPath() + path)) {
						if (Main.plugin.TabCompleteCommands.getConfig().getBoolean(subcommands.getCurrentPath() + path)) {
							return null;
						}
						subcommands = Main.plugin.TabCompleteCommands.getConfig().getConfigurationSection(subcommands.getCurrentPath() + path);
						List<String> list = new ArrayList<>();
						for (String sub : subcommands.getKeys(false)) {
							list.add(sub);
						}
						return list;
					}
				}
			}
		}
		return null;
	}
}