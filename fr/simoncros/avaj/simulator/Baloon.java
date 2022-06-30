package fr.simoncros.avaj.simulator;

public class Baloon extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	Baloon(String name, Coordinates coordinates) {
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
				Logs.print(this + ": Oh no, I don't like water!");
				he -= 5;
				break;
			case "FOG":
				Logs.print(this + ": Oh no, I don't like fog!");
				he -= 3;
				break;
			case "SUN":
				Logs.print(this + ": Oh no, I don't like sun!");
				lo += 2;
				he += 4;
				break;
			case "SNOW":
				Logs.print(this + ": Oh no, I don't like snow!");
				he -= 15;
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
