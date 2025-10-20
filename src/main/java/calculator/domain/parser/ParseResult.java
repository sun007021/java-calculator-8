package calculator.domain.parser;

import calculator.domain.delimiter.Delimiters;

public class ParseResult {
    private final Delimiters delimiters;
    private final String numberText;

    public ParseResult(Delimiters delimiters, String numberText) {
        this.delimiters = delimiters;
        this.numberText = numberText;
    }

    public Delimiters getDelimiters() {
        return delimiters;
    }

    public String getNumberText() {
        return numberText;
    }
}