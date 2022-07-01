package fr.simoncros.avaj.simulator;

public class WeatherProvider {
	private static WeatherProvider weatherProvider = new WeatherProvider();
	private static String[] weather = { "RAIN", "FOG", "SUN", "SNOW" };

	private WeatherProvider() {
		WeatherProvider.weatherProvider = this;
	}

	public static WeatherProvider getProvider() {
		return WeatherProvider.weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates) {
		int sum = coordinates.getLongitude() * coordinates.getLatitude() + coordinates.getHeight() % 4;
		return weather[(int) ((sum / 7) % weather.length)];
	}
}
