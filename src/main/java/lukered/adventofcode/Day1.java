package lukered.adventofcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static lukered.adventofcode.AdventOfCodeApplication.readFile;

@Slf4j
public class Day1 {
    public static void main(String[] args) {
        String s = readFile("Day1.txt");
        List<Long> calories = Arrays.stream(s.split("\n\n"))
                .map(e -> Arrays.stream(e.split("\n"))
                        .filter(x -> !x.isEmpty())
                        .mapToLong(Long::valueOf).sum())
                .sorted(Comparator.reverseOrder())
                .toList();

        log.info("#1: {} kcal, First three: {} kcal", calories.get(0),
                calories.get(0) + calories.get(1) + calories.get(2));
    }
}
