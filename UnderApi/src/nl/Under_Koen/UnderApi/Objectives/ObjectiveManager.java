package nl.Under_Koen.UnderApi.Objectives;

import org.bukkit.event.Listener;

import nl.Under_Koen.UnderApi.Main;

public class ObjectiveManager {
	
	/**
	 * If your Objective implements Listener than it will registerEvents
	 */
	public static void registerObjective (Objective o) {
		if (o instanceof Listener) {
			Listener listener = (Listener) o;
			Main.plugin.getServer().getPluginManager().registerEvents(listener, Main.plugin);
		}
		if (!o.isRegistered()) {
			o.register();
		}
	}
}
