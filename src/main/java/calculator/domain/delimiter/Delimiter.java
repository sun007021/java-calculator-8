package calculator.domain.delimiter;

public class Delimiter {
    private final String value;

    public Delimiter(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (value == null || value.length() != 1) {
            throw new IllegalArgumentException("구분자는 1개의 문자여야 합니다.");
        }
        if (Character.isDigit(value.charAt(0))) {
            throw new IllegalArgumentException("구분자는 숫자가 될 수 없습니다.");
        }
    }

    public String getValue() {
        return value;
    }
}