package fr.simoncros.avaj.simulator;

public class AircraftFactory {

	public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws UnknownFlyableTypeException {
		Coordinates coordinates = new Coordinates(longitude, latitude, height);
		switch (type) {
			case "JetPlane":
				return new JetPlane(name, coordinates);
			case "Helicopter":
				return new Helicopter(name, coordinates);
			case "Baloon":
				return new Baloon(name, coordinates);
			default:
				throw new UnknownFlyableTypeException(type);
		}
	}
}
