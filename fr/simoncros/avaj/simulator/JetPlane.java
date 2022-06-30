package fr.simoncros.avaj.simulator;

public class JetPlane extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		String weather = this.weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "RAIN":
				this.coordinates = new Coordinates(
						this.coordinates.getLongitude(),
						this.coordinates.getLatitude() + 5,
						this.coordinates.getHeight());
				break;
			case "FOG":
				this.coordinates = new Coordinates(
						this.coordinates.getLongitude(),
						this.coordinates.getLatitude() + 1,
						this.coordinates.getHeight());
				break;
			case "SUN":
				this.coordinates = new Coordinates(
						this.coordinates.getLongitude(),
						this.coordinates.getLatitude() + 10,
						this.coordinates.getHeight() + 2);
				break;
			case "SNOW":
				this.coordinates = new Coordinates(
						this.coordinates.getLongitude(),
						this.coordinates.getLatitude(),
						this.coordinates.getHeight() - 7);
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
