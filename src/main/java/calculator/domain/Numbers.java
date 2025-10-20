package calculator.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Numbers {
    private final List<PositiveInteger> values;

    public Numbers(List<PositiveInteger> values) {
        this.values = new ArrayList<>(values);
    }

    public static Numbers from(String[] tokens) {
        if (tokens.length == 0) {
            return empty();
        }

        List<PositiveInteger> numbers = Arrays.stream(tokens)
                .map(String::trim)
                .map(PositiveInteger::from)
                .collect(Collectors.toList());

        return new Numbers(numbers);
    }

    public int sum() {
        return values.stream()
                .mapToInt(PositiveInteger::getValue)
                .sum();
    }

    public static Numbers empty() {
        return new Numbers(new ArrayList<>());
    }
}