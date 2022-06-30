package fr.simoncros.avaj.simulator;

public class Baloon extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		String weather = this.weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "RAIN":
				this.coordinates = new Coordinates(
						this.coordinates.getLongitude(),
						this.coordinates.getLatitude(),
						this.coordinates.getHeight() - 5);
				break;
			case "FOG":
				this.coordinates = new Coordinates(
						this.coordinates.getLongitude(),
						this.coordinates.getLatitude(),
						this.coordinates.getHeight() - 3);
				break;
			case "SUN":
				this.coordinates = new Coordinates(
						this.coordinates.getLongitude() + 2,
						this.coordinates.getLatitude(),
						this.coordinates.getHeight() + 4);
				break;
			case "SNOW":
				this.coordinates = new Coordinates(
						this.coordinates.getLongitude(),
						this.coordinates.getLatitude(),
						this.coordinates.getHeight() - 15);
				break;
			default:
				break;
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
