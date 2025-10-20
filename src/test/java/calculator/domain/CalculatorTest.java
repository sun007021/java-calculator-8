package calculator.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    // ========== 정상 케이스 ==========

    @Test
    @DisplayName("기본 구분자(쉼표)로 숫자를 계산할 수 있다")
    void calculateWithComma() {
        // given
        String input = "1,2,3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("기본 구분자(콜론)로 숫자를 계산할 수 있다")
    void calculateWithColon() {
        // given
        String input = "1:2:3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("기본 구분자(쉼표와 콜론)를 혼용하여 계산할 수 있다")
    void calculateWithMixedDefaultDelimiters() {
        // given
        String input = "1,2:3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자로 숫자를 계산할 수 있다")
    void calculateWithCustomDelimiter() {
        // given
        String input = "//;\\n1;2;3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자와 기본 구분자를 혼용하여 계산할 수 있다")
    void calculateWithMixedCustomAndDefaultDelimiters() {
        // given
        String input = "//;\\n1;2,3:4";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("빈 입력이면 0을 반환한다")
    void calculateWithEmptyInput() {
        // given
        String input = "";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("단일 숫자를 계산할 수 있다")
    void calculateWithSingleNumber() {
        // given
        String input = "42";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @DisplayName("큰 숫자들을 계산할 수 있다")
    void calculateWithLargeNumbers() {
        // given
        String input = "10000,20000,30000";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(60000);
    }

    @Test
    @DisplayName("공백이 포함된 입력을 계산할 수 있다")
    void calculateWithWhitespace() {
        // given
        String input = " 1 , 2 , 3 ";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("특수문자를 커스텀 구분자로 사용하여 계산할 수 있다")
    void calculateWithSpecialCharacterDelimiter() {
        // given
        String input = "//|\\n1|2|3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("많은 숫자들을 계산할 수 있다")
    void calculateWithManyNumbers() {
        // given
        String input = "1,2,3,4,5,6,7,8,9,10";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(55);
    }

    // ========== 예외 케이스 ==========

    @Test
    @DisplayName("0이 포함되면 예외가 발생한다")
    void calculateWithZero() {
        // given
        String input = "1,0,3";

        // when & then
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양의 정수");
    }

    @Test
    @DisplayName("음수가 포함되면 예외가 발생한다")
    void calculateWithNegativeNumber() {
        // given
        String input = "1,-2,3";

        // when & then
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양의 정수");
    }

    @Test
    @DisplayName("숫자가 아닌 문자가 포함되면 예외가 발생한다")
    void calculateWithNonNumericCharacter() {
        // given
        String input = "1,abc,3";

        // when & then
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효한 숫자 형식이 아닙니다");
    }

    @Test
    @DisplayName("연속된 구분자가 있으면 예외가 발생한다")
    void calculateWithConsecutiveDelimiters() {
        // given
        String input = "1,,2";

        // when & then
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자가 연속으로");
    }

    @Test
    @DisplayName("커스텀 구분자 형식에서 \\n이 없으면 예외가 발생한다")
    void calculateWithInvalidCustomDelimiterFormat() {
        // given
        String input = "//;1;2;3";

        // when & then
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("커스텀 구분자 형식이 올바르지 않습니다");
    }

    @Test
    @DisplayName("커스텀 구분자가 지정되지 않으면 예외가 발생한다")
    void calculateWithMissingCustomDelimiter() {
        // given
        String input = "//\\n1,2,3";

        // when & then
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("커스텀 구분자");
    }

    @Test
    @DisplayName("커스텀 구분자가 2개 이상의 문자이면 예외가 발생한다")
    void calculateWithMultipleCharacterDelimiter() {
        // given
        String input = "//;;\\n1;;2;;3";

        // when & then
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1개의 문자");
    }

    @Test
    @DisplayName("커스텀 구분자에 숫자가 들어가면 예외가 발생한다")
    void calculateWithNumericDelimiter() {
        // given
        String input = "//5\\n152";

        // when & then
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자는 숫자가 될 수 없습니다");
    }

    @Test
    @DisplayName("공백만 있는 토큰이 있으면 예외가 발생한다")
    void calculateWithWhitespaceOnlyToken() {
        // given
        String input = "1, ,2";

        // when & then
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자가 연속으로");
    }

    @Test
    @DisplayName("시작이 구분자이면 예외가 발생한다")
    void calculateStartingWithDelimiter() {
        // given
        String input = ",1,2";

        // when & then
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자가 연속으로");
    }

    @Test
    @DisplayName("소수가 포함되면 예외가 발생한다")
    void calculateWithDecimal() {
        // given
        String input = "1.5,2,3";

        // when & then
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효한 숫자 형식이 아닙니다");
    }

    @Test
    @DisplayName("커스텀 구분자 이후 숫자가 없으면 0을 반환한다")
    void calculateWithCustomDelimiterButNoNumbers() {
        // given
        String input = "//;\\n";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("다양한 구분자가 섞인 복잡한 입력을 계산할 수 있다")
    void calculateWithComplexInput() {
        // given
        String input = "//;\\n1;2,3:4;5,6:7";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(28);
    }
}