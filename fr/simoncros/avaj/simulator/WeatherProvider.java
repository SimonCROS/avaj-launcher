package fr.simoncros.avaj.simulator;

public class WeatherProvider {
	private static WeatherProvider weatherProvider;
	private static String[] weather = { "RAIN", "FOG", "SUN", "SNOW" };

	private WeatherProvider() {
		WeatherProvider.weatherProvider = this;
	}

	public static WeatherProvider getProvider() {
		if (WeatherProvider.weatherProvider == null)
			WeatherProvider.weatherProvider = new WeatherProvider();
		return WeatherProvider.weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates) {
		int sum = coordinates.getLongitude() * coordinates.getLatitude() + coordinates.getHeight() % 4;
		return weather[(int) ((sum / 7) % weather.length)];
	}
}
