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
				lo += 5;
				break;
			case "FOG":
				lo += 1;
				break;
			case "SUN":
				lo += 10;
				he += 2;
				break;
			case "SNOW":
				he -= 12;
				break;
			default:
				break;
		}
		if (he <= 0) {
			he = 0;
			// PRINT
		}
		if (he > 100) {
			he = 100;
		}
		this.coordinates = new Coordinates(lo, la, he);
	}

	@Override
	public void registerTower(WeatherTower weatherTower) {
		if (this.weatherTower != null)
			this.weatherTower.unregister(this);
		this.weatherTower = weatherTower;
		weatherTower.register(this);
	}
}
