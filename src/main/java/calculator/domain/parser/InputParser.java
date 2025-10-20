package calculator.domain.parser;

import calculator.domain.delimiter.Delimiter;
import calculator.domain.delimiter.Delimiters;

public class InputParser {
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";

    public void parse(String input) {
        if (input == null || input.isEmpty()) {
            // return ""
        }

        if (hasCustomDelimiter(input)) {
            System.out.println(extractCustomDelimiter(input));
        }

    }

    private boolean hasCustomDelimiter(String input) {
        return input.startsWith(CUSTOM_DELIMITER_PREFIX);
    }

    private String extractCustomDelimiter(String input) {
        int delimiterStart = CUSTOM_DELIMITER_PREFIX.length();
        int delimiterEnd = input.indexOf(CUSTOM_DELIMITER_SUFFIX);

        if (delimiterEnd <= delimiterStart) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않았습니다.");
        }

        return input.substring(delimiterStart, delimiterEnd);
    }

}