package calculator.domain.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputParserTest {

    private final InputParser parser = new InputParser();

    @Test
    @DisplayName("일반 입력을 파싱할 수 있다")
    void parseNormalInput() {
        // given
        String input = "1,2,3";

        // when
        ParseResult result = parser.parse(input);

        // then
        assertThat(result.getNumberText()).isEqualTo("1,2,3");
        String[] tokens = result.splitNumbers();
        assertThat(tokens).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("커스텀 구분자가 있는 입력을 파싱할 수 있다")
    void parseWithCustomDelimiter() {
        // given
        String input = "//;\\n1;2;3";

        // when
        ParseResult result = parser.parse(input);

        // then
        assertThat(result.getNumberText()).isEqualTo("1;2;3");
        String[] tokens = result.splitNumbers();
        assertThat(tokens).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("커스텀 구분자와 기본 구분자를 혼용한 입력을 파싱할 수 있다")
    void parseWithMixedDelimiters() {
        // given
        String input = "//;\\n1;2,3:4";

        // when
        ParseResult result = parser.parse(input);

        // then
        assertThat(result.getNumberText()).isEqualTo("1;2,3:4");
        String[] tokens = result.splitNumbers();
        assertThat(tokens).containsExactly("1", "2", "3", "4");
    }

    @Test
    @DisplayName("빈 입력을 파싱하면 빈 numberText를 반환한다")
    void parseEmptyInput() {
        // given
        String input = "";

        // when
        ParseResult result = parser.parse(input);

        // then
        assertThat(result.getNumberText()).isEmpty();
        String[] tokens = result.splitNumbers();
        assertThat(tokens).isEmpty();
    }

    @Test
    @DisplayName("null 입력을 파싱하면 빈 numberText를 반환한다")
    void parseNullInput() {
        // given
        String input = null;

        // when
        ParseResult result = parser.parse(input);

        // then
        assertThat(result.getNumberText()).isEmpty();
    }

    @Test
    @DisplayName("커스텀 구분자 형식에서 \\n이 없으면 예외가 발생한다")
    void parseWithoutNewline() {
        // given
        String input = "//;1;2;3";

        // when & then
        assertThatThrownBy(() -> parser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("커스텀 구분자 형식이 올바르지 않습니다");
    }

    @Test
    @DisplayName("커스텀 구분자가 지정되지 않으면 예외가 발생한다")
    void parseWithoutDelimiter() {
        // given
        String input = "//\\n1,2,3";

        // when & then
        assertThatThrownBy(() -> parser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("커스텀 구분자");
    }

    @Test
    @DisplayName("커스텀 구분자가 2개 이상의 문자이면 예외가 발생한다")
    void parseWithMultipleCharacterDelimiter() {
        // given
        String input = "//;;\\n1;;2;;3";

        // when & then
        assertThatThrownBy(() -> parser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1개의 문자");
    }

    @Test
    @DisplayName("특수문자를 커스텀 구분자로 사용할 수 있다")
    void parseWithSpecialCharacterDelimiter() {
        // given
        String input = "//|\\n1|2|3";

        // when
        ParseResult result = parser.parse(input);

        // then
        assertThat(result.getNumberText()).isEqualTo("1|2|3");
        String[] tokens = result.splitNumbers();
        assertThat(tokens).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("커스텀 구분자 이후 숫자가 없어도 파싱할 수 있다")
    void parseWithCustomDelimiterButNoNumbers() {
        // given
        String input = "//;\\n";

        // when
        ParseResult result = parser.parse(input);

        // then
        assertThat(result.getNumberText()).isEmpty();
    }

    @Test
    @DisplayName("커스텀 구분자에 숫자가 들어가면 예외가 발생한다")
    void parseWithNumericDelimiter() {
        // given
        String input = "//5\\n152";

        // when & then
        assertThatThrownBy(() -> parser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자는 숫자가 될 수 없습니다");
    }
}
