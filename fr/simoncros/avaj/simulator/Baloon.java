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
				he -= 5;
				// LOG
				break;
			case "FOG":
				he -= 3;
				// LOG
				break;
			case "SUN":
				lo += 2;
				he += 4;
				// LOG
				break;
			case "SNOW":
				he -= 15;
				// LOG
				break;
			default:
				break;
		}
		if (he <= 0) {
			he = 0;
			// LOG
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
