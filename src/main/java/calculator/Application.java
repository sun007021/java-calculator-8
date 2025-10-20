package calculator;

import calculator.domain.Calculator;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Calculator calculator = new Calculator();
        CalculatorController calculatorController = new CalculatorController(inputView, outputView, calculator);
        calculatorController.run();
    }
}
