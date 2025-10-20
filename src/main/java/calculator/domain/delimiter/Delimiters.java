package calculator.domain.delimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Delimiters {
    private static final Delimiter COMMA = new Delimiter(",");
    private static final Delimiter COLON = new Delimiter(":");

    private final List<Delimiter> delimiters;

    public Delimiters(Delimiter... customDelimiters) {
        this.delimiters = new ArrayList<>();
        this.delimiters.add(COMMA);
        this.delimiters.add(COLON);

        for (Delimiter custom : customDelimiters) {
            this.delimiters.add(custom);
        }
    }

    public String[] split(String text) {
        if (text == null || text.isEmpty()) {
            return new String[0];
        }

        String regex = toRegex();
        String[] tokens = text.split(regex);

        // 빈 토큰 검증 (연속 구분자)
        for (String token : tokens) {
            if (token.trim().isEmpty()) {
                throw new IllegalArgumentException("구분자가 연속으로 사용되었거나 숫자가 누락되었습니다.");
            }
        }

        return tokens;
    }

    private String toRegex() {
        return delimiters.stream()
                .map(Delimiter::getValue)
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
    }
}