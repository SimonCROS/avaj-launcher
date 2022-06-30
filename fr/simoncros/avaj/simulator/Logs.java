package fr.simoncros.avaj.simulator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class Logs {
	private static List<String> lines = new ArrayList<>();

	public static void print(String line) {
		lines.add(line);
	}

	public static void write(String dest) throws IOException {
		Files.write(Paths.get(dest), Logs.lines, StandardCharsets.UTF_8);
	}
}
