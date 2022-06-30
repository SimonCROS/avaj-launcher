package fr.simoncros.avaj.simulator;

public class JetPlane extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		int lo = this.coordinates.getLongitude();
		int la = this.coordinates.getLatitude();
		int he = this.coordinates.getHeight();
		if (this.weatherTower != null) {
			String weather = this.weatherTower.getWeather(this.coordinates);
			switch (weather) {
				case "RAIN":
					Logs.print(this + ": Let's do the emptying, it won't be seen");
					la += 5;
					break;
				case "FOG":
					Logs.print(this + ": I see grey... everywhere");
					la += 1;
					break;
				case "SUN":
					Logs.print(this + ": Did you take sunglasses?");
					la += 10;
					he += 2;
					break;
				case "SNOW":
					Logs.print(this + ": This snow is really snowy...");
					he -= 7;
					break;
				default:
					break;
			}
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
