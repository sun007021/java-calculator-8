package calculator.domain.parser;

import calculator.domain.delimiter.Delimiter;
import calculator.domain.delimiter.Delimiters;

public class InputParser {
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";

    public ParseResult parse(String input) {
        if (input == null || input.isEmpty()) {
            return new ParseResult(new Delimiters(), "");
        }

        if (hasCustomDelimiter(input)) {
            return parseWithCustomDelimiter(input);
        }

        return new ParseResult(new Delimiters(), input);
    }

    private boolean hasCustomDelimiter(String input) { // 커스텀 구분자 있는지 확인
        return input.startsWith(CUSTOM_DELIMITER_PREFIX);
    }

    private ParseResult parseWithCustomDelimiter(String input) { // 커스텀 구분자있는 경우 파싱
        validateCustomDelimiterFormat(input);

        String customDelimiterChar = extractCustomDelimiter(input); // 커스텀 구분자 추출
        Delimiter customDelimiter = new Delimiter(customDelimiterChar); // Delimiter 객체 생성

        String numberText = extractNumberText(input); // 숫자시작하는 문자열 추출

        return new ParseResult(new Delimiters(customDelimiter), numberText); // ParseResult 객체 생성 및 반환
    }

    private void validateCustomDelimiterFormat(String input) { // 커스텀 구분자 형식 검증
        if (!input.contains(CUSTOM_DELIMITER_SUFFIX)) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다. 형식: //[구분자]\\n[숫자]");
        }
    }

    private String extractCustomDelimiter(String input) { // 커스텀 구분자 추출
        int delimiterStart = CUSTOM_DELIMITER_PREFIX.length();
        int delimiterEnd = input.indexOf(CUSTOM_DELIMITER_SUFFIX);

        if (delimiterEnd <= delimiterStart) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
        }

        return input.substring(delimiterStart, delimiterEnd);
    }

    private String extractNumberText(String input) { // 숫자시작하는 문자열 추출
        int numberStart = input.indexOf(CUSTOM_DELIMITER_SUFFIX) + CUSTOM_DELIMITER_SUFFIX.length();
        return input.substring(numberStart);
    }
}
