package calculator.domain.delimiter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DelimitersTest {

    @Test
    @DisplayName("기본 구분자(쉼표)로 문자열을 분리할 수 있다")
    void splitByComma() {
        // given
        Delimiters delimiters = new Delimiters();
        String text = "1,2,3";

        // when
        String[] result = delimiters.split(text);

        // then
        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("기본 구분자(콜론)로 문자열을 분리할 수 있다")
    void splitByColon() {
        // given
        Delimiters delimiters = new Delimiters();
        String text = "1:2:3";

        // when
        String[] result = delimiters.split(text);

        // then
        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("기본 구분자(쉼표와 콜론)를 혼용하여 분리할 수 있다")
    void splitByMixedDefaultDelimiters() {
        // given
        Delimiters delimiters = new Delimiters();
        String text = "1,2:3";

        // when
        String[] result = delimiters.split(text);

        // then
        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("커스텀 구분자를 추가하여 분리할 수 있다")
    void splitByCustomDelimiter() {
        // given
        Delimiter customDelimiter = new Delimiter(";");
        Delimiters delimiters = new Delimiters(customDelimiter);
        String text = "1;2;3";

        // when
        String[] result = delimiters.split(text);

        // then
        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("커스텀 구분자와 기본 구분자를 혼용하여 분리할 수 있다")
    void splitByMixedCustomAndDefaultDelimiters() {
        // given
        Delimiter customDelimiter = new Delimiter(";");
        Delimiters delimiters = new Delimiters(customDelimiter);
        String text = "1;2,3:4";

        // when
        String[] result = delimiters.split(text);

        // then
        assertThat(result).containsExactly("1", "2", "3", "4");
    }

    @Test
    @DisplayName("빈 문자열을 분리하면 빈 배열을 반환한다")
    void splitEmptyString() {
        // given
        Delimiters delimiters = new Delimiters();
        String text = "";

        // when
        String[] result = delimiters.split(text);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("null을 분리하면 빈 배열을 반환한다")
    void splitNull() {
        // given
        Delimiters delimiters = new Delimiters();

        // when
        String[] result = delimiters.split(null);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("연속된 구분자가 있으면 예외가 발생한다")
    void splitWithConsecutiveDelimiters() {
        // given
        Delimiters delimiters = new Delimiters();
        String text = "1,,2";

        // when & then
        assertThatThrownBy(() -> delimiters.split(text))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자가 연속으로");
    }

    @Test
    @DisplayName("시작이 구분자이면 예외가 발생한다")
    void splitStartingWithDelimiter() {
        // given
        Delimiters delimiters = new Delimiters();
        String text = ",1,2";

        // when & then
        assertThatThrownBy(() -> delimiters.split(text))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자가 연속으로");
    }

    @Test
    @DisplayName("공백만 있는 토큰이 있으면 예외가 발생한다")
    void splitWithWhitespaceOnlyToken() {
        // given
        Delimiters delimiters = new Delimiters();
        String text = "1, ,2";  // 공백만 있는 토큰

        // when & then
        assertThatThrownBy(() -> delimiters.split(text))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자가 연속으로");
    }

    @Test
    @DisplayName("정규식 특수문자도 구분자로 사용할 수 있다")
    void splitByRegexSpecialCharacter() {
        // given
        Delimiter customDelimiter = new Delimiter("|");
        Delimiters delimiters = new Delimiters(customDelimiter);
        String text = "1|2|3";

        // when
        String[] result = delimiters.split(text);

        // then
        assertThat(result).containsExactly("1", "2", "3");
    }
}