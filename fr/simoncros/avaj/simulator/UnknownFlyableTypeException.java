package fr.simoncros.avaj.simulator;

public class UnknownFlyableTypeException extends Exception { 
	public UnknownFlyableTypeException(String type) {
		super("Unknown flyable type: " + type);
	}
}
