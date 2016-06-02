package nl.Under_Koen.UnderApi.Test;

import org.bukkit.Location;

import nl.Under_Koen.UnderApi.Area.Area;
import nl.Under_Koen.UnderApi.Area.AreaCorners;
import nl.Under_Koen.UnderApi.Area.AreaSpawn;

public class AreaTest implements Area, AreaCorners, AreaSpawn{

	@Override
	public int getId() {
		return 3;
	}

	@Override
	public String getName() {
		return "test";
	}

	@Override
	public void onLoad() {
		setFirstCorner(new Location(null, 10, 0, 10));
		setSecondCorner(new Location(null, -10, 0, -10));
		ignoreY();
		setSpawn(new Location(null, 0, 70, 0));
	}

}
