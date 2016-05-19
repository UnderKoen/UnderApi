package nl.Under_Koen.UnderApi.Area;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import nl.Under_Koen.UnderApi.Main;

public interface AreaPermissions extends Area {

	enum Type {
		ENTER_AREA(0), LEAVE_AREA(1), SPAWN_AREA(2), ENTER_PORTAL(3), SPAWN_PORTAL(4);
		
		private int id;
		
		Type(int id) {
			this.id = id;
		}
		
		public int getId() {
			return id;
		}
	}
	
	public HashMap<Integer, Boolean> enabled = new HashMap<Integer, Boolean>();

	default void enablePermission(Type t) {
		switch (t) {
		case ENTER_AREA:
			enabled.put(t.getId(), true);
			Permission permission = new Permission("UnderApi.Area.EnterArea." + getId());
			Main.plugin.getServer().getPluginManager().addPermission(permission);
			break;
		case ENTER_PORTAL:
			enabled.put(t.getId(), true);
			Permission permission2 = new Permission("UnderApi.Area.EnterPortal." + getId());
			Main.plugin.getServer().getPluginManager().addPermission(permission2);
			break;
		case LEAVE_AREA:
			enabled.put(t.getId(), true);
			Permission permission3 = new Permission("UnderApi.Area.LeaveArea." + getId());
			Main.plugin.getServer().getPluginManager().addPermission(permission3);
			break;
		case SPAWN_AREA:
			enabled.put(t.getId(), true);
			Permission permission4 = new Permission("UnderApi.Area.SpawnArea." + getId());
			Main.plugin.getServer().getPluginManager().addPermission(permission4);
			break;
		case SPAWN_PORTAL:
			enabled.put(t.getId(), true);
			Permission permission5 = new Permission("UnderApi.Area.SpawnPortal." + getId());
			Main.plugin.getServer().getPluginManager().addPermission(permission5);
			break;
		default:
			break;
		}
	}
	
	default void disablePermission(Type t) {
		switch (t) {
		case ENTER_AREA:
			enabled.remove(t.getId());
			Permission permission = new Permission("UnderApi.Area.EnterArea." + getId());
			Main.plugin.getServer().getPluginManager().removePermission(permission);
			break;
		case ENTER_PORTAL:
			enabled.remove(t.getId());
			Permission permission2 = new Permission("UnderApi.Area.EnterPortal." + getId());
			Main.plugin.getServer().getPluginManager().removePermission(permission2);
			break;
		case LEAVE_AREA:
			enabled.remove(t.getId());
			Permission permission3 = new Permission("UnderApi.Area.LeaveArea." + getId());
			Main.plugin.getServer().getPluginManager().removePermission(permission3);
			break;
		case SPAWN_AREA:
			enabled.remove(t.getId());
			Permission permission4 = new Permission("UnderApi.Area.SpawnArea." + getId());
			Main.plugin.getServer().getPluginManager().removePermission(permission4);
			break;
		case SPAWN_PORTAL:
			enabled.remove(t.getId());
			Permission permission5 = new Permission("UnderApi.Area.SpawnPortal." + getId());
			Main.plugin.getServer().getPluginManager().removePermission(permission5);
			break;
		default:
			break;
		}
	}
	
	default boolean isEnabled(Type t) {
		switch (t) {
		case ENTER_AREA:
			return enabled.containsKey(t.getId());
		case ENTER_PORTAL:
			return enabled.containsKey(t.getId());
		case LEAVE_AREA:
			return enabled.containsKey(t.getId());
		case SPAWN_AREA:
			return enabled.containsKey(t.getId());
		case SPAWN_PORTAL:
			return enabled.containsKey(t.getId());
		default:
			return false;
		}
	}

	default boolean hasPermission(Player p, Type t) {
		if (!isEnabled(t)) {
			return true;
		}
		switch (t) {
		case ENTER_AREA:
			return p.hasPermission("UnderApi.Area.EnterArea." + getId());
		case ENTER_PORTAL:
			return p.hasPermission("UnderApi.Area.EnterPortal." + getId());
		case LEAVE_AREA:
			return p.hasPermission("UnderApi.Area.LeaveArea." + getId());
		case SPAWN_AREA:
			return p.hasPermission("UnderApi.Area.SpawnArea." + getId());
		case SPAWN_PORTAL:
			return p.hasPermission("UnderApi.Area.SpawnPortal." + getId());
		default:
			return false;
		}
	}
}
