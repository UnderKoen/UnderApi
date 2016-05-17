package nl.Under_Koen.UnderApi.Area;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Space implements Area,AreaCorners,AreaSpawn {

	@Override
	public int getId() {
		return 417;
	}

	@Override
	public String getName() {
		return "OI";
	}

	@Override
	public void onLoad() {
		setSpawn(new Location(Bukkit.getWorld("World"), 0, 70, 0));
		setFirstCorner(new Location(Bukkit.getWorld("World"), 10, 60, -10));
		setSecondCorner(new Location(Bukkit.getWorld("World"), -10, 100, 10));
	}

}
