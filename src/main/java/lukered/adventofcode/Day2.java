package lukered.adventofcode;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static lukered.adventofcode.AdventOfCodeApplication.readStream;

@Slf4j
public class Day2 {
    public static void main(String[] args) {
        final int sum = readStream("Day2.txt")
                .map(x -> RPS.getFromRaw(x.charAt(2)).fight(x.charAt(0)))
                .mapToInt(Integer::intValue).sum();
        log.info("Result: {}", sum);
        final int sum2 = readStream("Day2.txt")
                .map(x -> RPS.getFromRaw(x.charAt(0)).fightWithResult(x.charAt(2)))
                .mapToInt(Integer::intValue).sum();
        log.info("Result with predefined outcome: {}", sum2);
    }



    enum RPS {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);

        @Getter
        final int value;
        RPS(final int value) {
            this.value = value;
        }

        public static RPS getFromRaw(final char x) {
            return switch (x) {
                case 'A','X' -> ROCK;
                case 'B','Y' -> PAPER;
                case 'C','Z' -> SCISSORS;
                default -> throw new IndexOutOfBoundsException("UNKNOWN CHAR " + x);
            };
        }

        public int fight(final char enemy) {
            return fight(getFromRaw(enemy));
        }
        public int fight(final RPS enemy) {
            return this.value + switch ((this.value - enemy.getValue() + 2) % 3) {
                case 0 -> {
                    log.info("{} - {} : WIN ({})", this, enemy, this.value + 6);
                    yield 6; // WIN
                }
                case 1 -> {
                    log.info("{} - {} : LOOSE ({})", this, enemy, this.value);
                    yield 0; // LOOSE
                }
                case 2 -> {
                    log.info("{} - {} : DRAW ({})", this, enemy, this.value + 3);
                    yield 3; // DRAW
                }
                default -> throw new IndexOutOfBoundsException();
            };
        }
        public int fightWithResult(final char result) {
            return switch (result) {
                case 'X' -> decrement(this.value);  // LOOSE
                case 'Y' -> this.value + 3; // DRAW
                case 'Z' -> this.value % 3 + 1 + 6; // WIN
                default -> throw new IndexOutOfBoundsException("UNKNOWN CHAR " + result);
            };
        }

        public static int decrement(final int i) {
            if ( i <= 1 ) {
                return 3;
            }
            return i-1;
        }


    }
}
