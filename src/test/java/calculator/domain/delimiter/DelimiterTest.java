package calculator.domain.delimiter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DelimiterTest {

    @Test
    @DisplayName("1개 문자로 Delimiter를 생성할 수 있다")
    void createWithSingleCharacter() {
        // given & when
        Delimiter delimiter = new Delimiter(",");

        // then
        assertThat(delimiter.getValue()).isEqualTo(",");
    }

    @Test
    @DisplayName("특수문자로 Delimiter를 생성할 수 있다")
    void createWithSpecialCharacter() {
        // given & when
        Delimiter delimiter = new Delimiter(";");

        // then
        assertThat(delimiter.getValue()).isEqualTo(";");
    }

    @Test
    @DisplayName("null로 Delimiter를 생성하면 예외가 발생한다")
    void createWithNull() {
        // given & when & then
        assertThatThrownBy(() -> new Delimiter(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1개의 문자");
    }

    @Test
    @DisplayName("빈 문자열로 Delimiter를 생성하면 예외가 발생한다")
    void createWithEmptyString() {
        // given & when & then
        assertThatThrownBy(() -> new Delimiter(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1개의 문자");
    }

    @Test
    @DisplayName("2개 이상의 문자로 Delimiter를 생성하면 예외가 발생한다")
    void createWithMultipleCharacters() {
        // given & when & then
        assertThatThrownBy(() -> new Delimiter(",,"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1개의 문자");
    }
}