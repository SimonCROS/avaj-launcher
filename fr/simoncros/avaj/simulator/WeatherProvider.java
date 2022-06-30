package fr.simoncros.avaj.simulator;

public class WeatherProvider {
	private static WeatherProvider weatherProvider;
	private static String[] weather = { "RAIN", "FOG", "SUN", "SLOW" };

	private WeatherProvider() {
		WeatherProvider.weatherProvider = this;
	}

	public static WeatherProvider getProvider() {
		return WeatherProvider.weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates) {
		return weather[(coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight())
				% weather.length];
	}
}
