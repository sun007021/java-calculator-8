package calculator.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositiveIntegerTest {

    @Test
    @DisplayName("양의 정수로 PositiveInteger를 생성할 수 있다")
    void createWithPositiveNumber() {
        // given & when
        PositiveInteger positiveInteger = new PositiveInteger(1);

        // then
        assertThat(positiveInteger.getValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("0으로 PositiveInteger를 생성하면 예외가 발생한다")
    void createWithZero() {
        // given & when & then
        assertThatThrownBy(() -> new PositiveInteger(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양의 정수");
    }

    @Test
    @DisplayName("음수로 PositiveInteger를 생성하면 예외가 발생한다")
    void createWithNegativeNumber() {
        // given & when & then
        assertThatThrownBy(() -> new PositiveInteger(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양의 정수");
    }

    @Test
    @DisplayName("문자열로부터 PositiveInteger를 생성할 수 있다")
    void fromString() {
        // given
        String input = "123";

        // when
        PositiveInteger positiveInteger = PositiveInteger.from(input);

        // then
        assertThat(positiveInteger.getValue()).isEqualTo(123);
    }

    @Test
    @DisplayName("공백이 포함된 문자열로부터 PositiveInteger를 생성할 수 있다")
    void fromStringWithWhitespace() {
        // given
        String input = "  456  ";

        // when
        PositiveInteger positiveInteger = PositiveInteger.from(input);

        // then
        assertThat(positiveInteger.getValue()).isEqualTo(456);
    }

    @Test
    @DisplayName("숫자가 아닌 문자열로부터 생성하면 예외가 발생한다")
    void fromInvalidString() {
        // given
        String input = "abc";

        // when & then
        assertThatThrownBy(() -> PositiveInteger.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효한 숫자 형식이 아닙니다");
    }

    @Test
    @DisplayName("0을 나타내는 문자열로부터 생성하면 예외가 발생한다")
    void fromZeroString() {
        // given
        String input = "0";

        // when & then
        assertThatThrownBy(() -> PositiveInteger.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양의 정수");
    }

    @Test
    @DisplayName("음수를 나타내는 문자열로부터 생성하면 예외가 발생한다")
    void fromNegativeString() {
        // given
        String input = "-5";

        // when & then
        assertThatThrownBy(() -> PositiveInteger.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양의 정수");
    }

    @Test
    @DisplayName("getValue()는 저장된 값을 반환한다")
    void getValue() {
        // given
        PositiveInteger positiveInteger = new PositiveInteger(100);

        // when
        int value = positiveInteger.getValue();

        // then
        assertThat(value).isEqualTo(100);
    }
}