package nl.Under_Koen.UnderApi.Test;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import nl.Under_Koen.UnderApi.Objectives.Objective;

public class Teleport implements Listener, Objective {
	
	public Teleport(int specialId) {
		setSpecialId(specialId);
	}
	
	public Teleport() {
	}
	
	@EventHandler
	public void onMoneyUpdate(PlayerTeleportEvent e) {
		updateAdd(e.getPlayer(), 1);
	}
	
	@Override
	public String getName() {
		return "tp";
	}

	private boolean registered = false;
	
	@Override
	public void register() {
		registered = true;
	}

	@Override
	public boolean isRegistered() {
		return registered;
	}
	
	private int id;
	
	@Override
	public int getSpecialId() {
		return id;
	};
	
	public void setSpecialId(int id) {
		this.id= id;
	};
}