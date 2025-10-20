package calculator;

import calculator.domain.parser.InputParser;

public class CalculatorController {
    private final InputView inputView;
    private final OutputView outputView;
    private final InputParser inputParser;

    public CalculatorController(InputView inputView, OutputView outputView, InputParser inputParser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputParser = inputParser;
    }

    public void run() {
        String input = inputView.readCalculationInput();
        inputParser.parse(input);
        outputView.printCalculationOutput(0);
    }
}
