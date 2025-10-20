package calculator.domain;

import calculator.domain.parser.InputParser;
import calculator.domain.parser.ParseResult;

public class Calculator {
    private final InputParser inputParser;

    public Calculator() {
        this.inputParser = new InputParser();
    }

    public int calculate(String input) {
        // 1. 파싱: 커스텀 구분자 추출, 본문 추출
        ParseResult parseResult = inputParser.parse(input);

        // 2. 구분자를 이용한 문자열 분리
        String[] tokens = parseResult.splitNumbers();

        // 3. 분리된 문자열을 Numbers 객체로 변환 (PositiveInteger 검증 포함)
        Numbers numbers = Numbers.from(tokens);

        // 4. 계산
        return numbers.sum();
    }
}