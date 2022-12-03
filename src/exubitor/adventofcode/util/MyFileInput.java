package exubitor.adventofcode.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class MyFileInput {

    public static List<String> parseInputToStringList(File input) {
        try {
            return Files.readAllLines(input.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read file: " + e.getMessage());
        }
    }
}
