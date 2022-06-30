package fr.simoncros.avaj.simulator;

public class WeatherProvider {
	private static WeatherProvider weatherProvider;
	private static String[] weather = {"RAIN", "FOG", "SUN", "SLOW"};

	private WeatherProvider() {
		WeatherProvider.weatherProvider = this;
	}

	public static WeatherProvider getProvider() {
		return WeatherProvider.weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates) {
		int lo = coordinates.getLongitude() / 16 % weather.length;
		int la = coordinates.getLatitude() / 16 % weather.length;
		int he = coordinates.getHeight() / 16 % weather.length;
		return weather[(lo + la + he) % weather.length];
	}
}
