package fr.simoncros.avaj.simulator;

import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class Simulator {

	private static void exit(String message) {
		System.out.println(message);
		System.exit(1);
	}

	private static void run(List<String> lines) throws UnknownFlyableTypeException, NumberFormatException {
		WeatherTower tower = new WeatherTower();

		// Global file size check
		if (lines.size() < 1)
			exit("Not enough lines in the input file.");

		// Parse simulation duration
		String firstLine = lines.remove(0);
		if (firstLine.split(" ").length != 1)
			exit("Invalid first line.");
		int iterations = Integer.parseUnsignedInt(firstLine);

		// Parse all the flyables
		for (String line : lines) {
			String[] parts = line.split(" ");
			if (parts.length != 5)
				exit("Invalid line: \"" + line + "\"");
			Flyable flyable = AircraftFactory.newAircraft(parts[0], parts[1], Integer.parseUnsignedInt(parts[2]),
					Integer.parseUnsignedInt(parts[3]), Integer.parseUnsignedInt(parts[4]));
			flyable.registerTower(tower);
		}

		// Start simulation
		while (iterations > 0) {
			tower.changeWeather();
			--iterations;
		}
	}

	public static void main(String[] args) {
		// Check amount of arguments
		if (args.length != 1) exit("Only one parameter allowed.");

		// Exceptions handlers
		try {
			// Read the whole file
			List<String> lines = Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8);
			// Parse and run
			run(lines);
			// Write all the logs to simulation.txt
			Logs.write("simulation.txt");
		} catch (UnknownFlyableTypeException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage() + " is not a valid number");
			System.exit(1);
		} catch (NoSuchFileException e) {
			System.out.println(e.getFile() + " not found");
			System.exit(1);
		} catch (AccessDeniedException e) {
			System.out.println("Read/Write permission denied on " + e.getFile());
			System.exit(1);
		} catch (Exception e) {
			System.out.println(e.toString());
			System.exit(1);
		}
	}
}
