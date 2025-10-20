package calculator;

import calculator.domain.Calculator;

public class CalculatorController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Calculator calculator;

    public CalculatorController(InputView inputView, OutputView outputView, Calculator calculator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.calculator = calculator;
    }

    public void run() {
        String input = inputView.readCalculationInput();
        int result = calculator.calculate(input);
        outputView.printCalculationOutput(result);
    }
}
