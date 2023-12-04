package task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Base {
    static Map<String, String> values = Map.of(
        "one", "o1e",
        "two", "t2o",
        "three", "th3ee",
        "four", "f4ur",
        "five", "f5ve",
        "six", "s6x",
        "seven", "se7en",
        "eight", "ei8ht",
        "nine", "n9ne"
);

public static void main(String[] args) throws IOException {
    String input = Files.readString(Path.of("/Users/rickvanberlo/Projects/advent-of-code-2023/task1/1.txt"));

    for (Map.Entry<String, String> entry : values.entrySet()) {
        input = input.replaceAll(entry.getKey(), entry.getValue());
    }

    int sum = input.lines()
            .map(String::chars)
            .map(intStream -> intStream.filter(Character::isDigit).toArray())
            .map(ints -> (char) ints[0] + "" + (char) ints[ints.length - 1])
            .mapToInt(Integer::parseInt)
            .sum();

    System.out.println(sum);
}
}