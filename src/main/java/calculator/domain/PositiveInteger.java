package calculator.domain;

public class PositiveInteger {
    private final int value;

    public PositiveInteger(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("숫자는 양의 정수여야 합니다.");
        }
    }

    public static PositiveInteger from(String text) {
        try {
            int value = Integer.parseInt(text.trim());
            return new PositiveInteger(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효한 숫자 형식이 아닙니다: " + text);
        }
    }

    public int getValue() {
        return value;
    }
}