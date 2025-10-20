package calculator;

public class CalculatorController {
    private final InputView inputView;
    private final OutputView outputView;

    public CalculatorController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String input = inputView.readCalculationInput();
        outputView.printCalculationOutput(0);
    }
}
