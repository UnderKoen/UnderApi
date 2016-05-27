package nl.Under_Koen.UnderApi.Objectives;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Listener;

import nl.Under_Koen.UnderApi.Main;

public class ObjectiveManager {
	
	private final static List<Objective> objectives = new ArrayList<>();
	
	public static List<Objective> getObjectives() {
		return objectives;
	}
	
	/**
	 * If your Objective implements Listener than it will registerEvents
	 */
	public static void registerObjective (Objective o) {
		if (o instanceof Objectives) {
			o.register();
			return;
		}
		for (Objective o2: objectives) {
			if (o2.getFormatterString().contains(o.getFormatterString())) {
				o.register();
				return;
			}
		}
		objectives.add(o);
		if (o instanceof Listener) {
			Listener listener = (Listener) o;
			Main.plugin.getServer().getPluginManager().registerEvents(listener, Main.plugin);
		}
		if (!o.isRegistered()) {
			o.register();
		}
		ArrayList<String> list = new ArrayList<String>();
		for (Objective o2: objectives) {
			list.add(o2.getClass().getName()+":"+o2.getSpecialId());
		}
		Main.plugin.objectiveData.getConfig().set("list", list);
		Main.plugin.objectiveData.saveConfig();
	}
}
