package fr.simoncros.avaj.simulator;

public class Helicopter extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		int lo = this.coordinates.getLongitude();
		int la = this.coordinates.getLatitude();
		int he = this.coordinates.getHeight();
		String weather = this.weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "RAIN":
				Logs.print(this + ": I divide the drops into smaller drops");
				lo += 5;
				break;
			case "FOG":
				Logs.print(this + ": Let's pray that there is no mountain...");
				lo += 1;
				break;
			case "SUN":
				Logs.print(this + ": It's time to take photos");
				lo += 10;
				he += 2;
				break;
			case "SNOW":
				Logs.print(this + ": The landscapes are beautiful in white");
				he -= 12;
				break;
			default:
				break;
		}
		he = Math.max(0, Math.min(he, 100));
		this.coordinates = new Coordinates(lo, la, he);
		if (this.coordinates.getHeight() == 0) {
			Logs.print(this + " landing at " + this.coordinates);
			if (this.weatherTower != null)
				this.weatherTower.unregister(this);
		}
	}

	@Override
	public void registerTower(WeatherTower weatherTower) {
		if (this.weatherTower != null)
			this.weatherTower.unregister(this);
		this.weatherTower = weatherTower;
		weatherTower.register(this);
	}
}
