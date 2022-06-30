package fr.simoncros.avaj.simulator;

import java.io.FileInputStream;
import java.nio.file.NoSuchFileException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
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
			lines.forEach(System.out::println);
		} catch (NoSuchFileException e) {
			System.out.println(args[0] + " not found");
			System.exit(1);
		} catch (AccessDeniedException e) {
			System.out.println("Read permission denied on " + args[0]);
			System.exit(1);
		} catch (IOException e) {
			System.out.println(e.toString());
			System.exit(1);
		}

	}
}
