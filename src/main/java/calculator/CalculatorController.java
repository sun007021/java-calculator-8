package calculator;

public class CalculatorController {
    private final InputView inputView;

    public CalculatorController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        String input = inputView.readCalculationInput();
        System.out.println(input);
    }
}
