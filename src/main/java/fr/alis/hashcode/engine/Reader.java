package fr.alis.hashcode.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class Reader<I> {

    public I read(String path) {
        List<String> lines = getLines(path);
        return parse(lines);
    }

    protected abstract I parse(List<String> lines);

    public List<String> getLines(String filepath) {

        Path path = Paths.get(filepath);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            return lines;


        } catch (IOException ex) {
            throw new RuntimeException("Cannot read file " + path, ex);
        }
    }
}
