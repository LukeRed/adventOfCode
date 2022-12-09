package lukered.adventofcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdventOfCodeApplication {

    public static void main(String[] args) {
        String s = readFile("Day1.txt");
        System.out.println(s);
    }


    public static String readFile(final String name) {
        final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        final InputStream is = classloader.getResourceAsStream("inputs/" + name);
        return new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    public static Stream<String> readStream(final String name) {
        final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        final InputStream is = classloader.getResourceAsStream("inputs/" + name);
        return new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8))
                .lines()
                .filter(x -> !x.isEmpty());
    }
}
