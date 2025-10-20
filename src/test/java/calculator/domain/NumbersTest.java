package calculator.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumbersTest {

    @Test
    @DisplayName("PositiveInteger 리스트로 Numbers를 생성할 수 있다")
    void createWithList() {
        // given
        PositiveInteger one = new PositiveInteger(1);
        PositiveInteger two = new PositiveInteger(2);
        PositiveInteger three = new PositiveInteger(3);

        // when
        Numbers numbers = new Numbers(Arrays.asList(one, two, three));

        // then
        assertThat(numbers.sum()).isEqualTo(6);
    }

    @Test
    @DisplayName("빈 리스트로 Numbers를 생성할 수 있다")
    void createWithEmptyList() {
        // given & when
        Numbers numbers = new Numbers(Collections.emptyList());

        // then
        assertThat(numbers.sum()).isEqualTo(0);
    }

    @Test
    @DisplayName("empty()로 빈 Numbers를 생성할 수 있다")
    void empty() {
        // given & when
        Numbers numbers = Numbers.empty();

        // then
        assertThat(numbers.sum()).isEqualTo(0);
    }

    @Test
    @DisplayName("문자열 배열로부터 Numbers를 생성할 수 있다")
    void fromStringArray() {
        // given
        String[] tokens = {"1", "2", "3"};

        // when
        Numbers numbers = Numbers.from(tokens);

        // then
        assertThat(numbers.sum()).isEqualTo(6);
    }

    @Test
    @DisplayName("공백이 포함된 문자열 배열로부터 Numbers를 생성할 수 있다")
    void fromStringArrayWithWhitespace() {
        // given
        String[] tokens = {" 1 ", " 2 ", " 3 "};

        // when
        Numbers numbers = Numbers.from(tokens);

        // then
        assertThat(numbers.sum()).isEqualTo(6);
    }

    @Test
    @DisplayName("빈 배열로부터 empty Numbers를 생성한다")
    void fromEmptyArray() {
        // given
        String[] tokens = {};

        // when
        Numbers numbers = Numbers.from(tokens);

        // then
        assertThat(numbers.sum()).isEqualTo(0);
    }

    @Test
    @DisplayName("숫자가 아닌 문자열이 포함되면 예외가 발생한다")
    void fromArrayWithInvalidNumber() {
        // given
        String[] tokens = {"1", "abc", "3"};

        // when & then
        assertThatThrownBy(() -> Numbers.from(tokens))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효한 숫자 형식이 아닙니다");
    }

    @Test
    @DisplayName("0이 포함되면 예외가 발생한다")
    void fromArrayWithZero() {
        // given
        String[] tokens = {"1", "0", "3"};

        // when & then
        assertThatThrownBy(() -> Numbers.from(tokens))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양의 정수");
    }

    @Test
    @DisplayName("음수가 포함되면 예외가 발생한다")
    void fromArrayWithNegativeNumber() {
        // given
        String[] tokens = {"1", "-2", "3"};

        // when & then
        assertThatThrownBy(() -> Numbers.from(tokens))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양의 정수");
    }

    @Test
    @DisplayName("sum()은 모든 숫자의 합을 반환한다")
    void sum() {
        // given
        Numbers numbers = Numbers.from(new String[]{"1", "2", "3", "4", "5"});

        // when
        int result = numbers.sum();

        // then
        assertThat(result).isEqualTo(15);
    }

    @Test
    @DisplayName("빈 Numbers의 sum()은 0을 반환한다")
    void sumEmpty() {
        // given
        Numbers numbers = Numbers.empty();

        // when
        int result = numbers.sum();

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("단일 숫자의 sum()은 해당 숫자를 반환한다")
    void sumSingleNumber() {
        // given
        Numbers numbers = Numbers.from(new String[]{"42"});

        // when
        int result = numbers.sum();

        // then
        assertThat(result).isEqualTo(42);
    }
}