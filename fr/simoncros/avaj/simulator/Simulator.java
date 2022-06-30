package fr.simoncros.avaj.simulator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class Simulator {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Only one parameter allowed.");
			System.exit(1);
		}
		try {
			List<String> lines = Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8);
			if (lines.size() < 1) {
				System.out.println("Not enough lines in the input file.");
				System.exit(1);
			}
			String firstLine = lines.remove(0);
			if (firstLine.split(" ").length != 1) {
				System.out.println("Invalid first line.");
				System.exit(1);
			}
			int iterations = Integer.parseUnsignedInt(firstLine);
			for (String line : lines) {
				
			}
			Logs.write();
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage() + " is not a valid number");
			System.exit(1);
		} catch (NoSuchFileException e) {
			System.out.println(e.getFile() + " not found");
			System.exit(1);
		} catch (AccessDeniedException e) {
			System.out.println("Read/Write permission denied on " + e.getFile());
			System.exit(1);
		} catch (IOException e) {
			System.out.println(e.toString());
			System.exit(1);
		}
	}
}
