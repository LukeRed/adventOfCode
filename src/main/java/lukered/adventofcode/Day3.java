package lukered.adventofcode;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static lukered.adventofcode.AdventOfCodeApplication.readStream;

@Slf4j
public class Day3 {
    public static void main(String[] args) {
        int sum = readStream("Day3.txt")
                .flatMap(backpack -> {
                    final String compartment1 = backpack.substring(0, backpack.length() / 2);
                    final String compartment2 = backpack.substring(backpack.length() / 2);
                    return compartment1.chars()
                            .filter(c -> compartment2.contains(Character.toString(c)))
                            .distinct()
                            .mapToObj(e -> {
                                log.info("{} [ {} - {} ] = {} ({})", backpack, compartment1, compartment2, Character.toString(e), normalize(e));
                                return normalize(e);
                            });
                }).mapToInt(Integer::intValue).sum();
        log.info("Sum of priorities: {}", sum);

        List<String> strings = readStream("Day3.txt").toList();
        int sum2 = 0;
        for(int i = 0; i < strings.size(); i = i+3) {
            final int cur = i;
            sum2 += strings.get(i).chars()
                    .filter(c -> strings.get(cur+1).contains(Character.toString(c)))
                    .filter(c -> strings.get(cur+2).contains(Character.toString(c)))
                    .distinct()
                    .map(e -> {
                        log.info("Group #{} = {} ({})", cur / 3 , Character.toString(e), normalize(e));
                        return normalize(e);
                    })
                    .findFirst()
                    .getAsInt();
        }
        log.info("Sum of priorities: {}", sum2);
    }

    public static int normalize(final int character) {
        if(character > 96) {
            return character - 96;
        }
        return character - 38;
    }
}
