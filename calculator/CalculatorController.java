
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {

    private final CalculatorView view;
    private final CalculatorModel model;

    private double firstNum;
    private String operation = null;
    private boolean awaitingSecondNumber = false;
    private String currentMode;

    public CalculatorController(CalculatorView view, CalculatorModel model) {
        this.view = view;
        this.model = model;
        this.currentMode = view.getMode();

        // Digit listeners
        for (int i = 0; i < 10; i++) {
            view.addDigitListener(new DigitListener(i), i);
        }

        // Other listeners
        view.addDotListener(new DotListener());
        view.addAddListener(new OperationListener("+"));
        view.addSubListener(new OperationListener("-"));
        view.addMulListener(new OperationListener("*"));
        view.addDivListener(new OperationListener("/"));
        view.addSinListener(new UnaryOperationListener("sin"));
        view.addCosListener(new UnaryOperationListener("cos"));
        view.addSqrtListener(new UnaryOperationListener("sqrt"));
        view.addEqualsListener(new EqualsListener());
        view.addClearListener(new ClearListener());
        view.addModeListener(new ModeListener());
    }

    private boolean isBinary(String s) {
        return s.matches("[01]+");
    }

    private void performCalculation() {
        try {
            String input = view.getDisplayText();
            if (currentMode.equals("Binary")) {
                if (!isBinary(String.valueOf((int) firstNum)) || !isBinary(input)) {
                    throw new IllegalArgumentException("Invalid binary input");
                }
                String result;
                switch (operation) {
                    case "+":
                        result = model.addBinary(String.valueOf((int) firstNum), input);
                        break;
                    case "-":
                        result = model.subBinary(String.valueOf((int) firstNum), input);
                        break;
                    case "*":
                        result = model.mulBinary(String.valueOf((int) firstNum), input);
                        break;
                    case "/":
                        result = model.divBinary(String.valueOf((int) firstNum), input);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operation");
                }
                view.setDisplayText(result);
            } else {
                double secondNum = Double.parseDouble(input);
                double result;
                switch (operation) {
                    case "+":
                        result = model.add(firstNum, secondNum);
                        break;
                    case "-":
                        result = model.subtract(firstNum, secondNum);
                        break;
                    case "*":
                        result = model.multiply(firstNum, secondNum);
                        break;
                    case "/":
                        result = model.divide(firstNum, secondNum);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operation");
                }
                view.setDisplayText(String.valueOf(result));
            }
            operation = null;
            awaitingSecondNumber = false;
        } catch (NumberFormatException e) {
            view.showError("Invalid number format");
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        } catch (ArithmeticException e) {
            view.showError("Arithmetic error: " + e.getMessage());
        } catch (Exception e) {
            view.showError("An error occurred");
        }
    }

    private void performUnaryCalculation(String op) {
        try {
            String input = view.getDisplayText();
            double result;
            if (currentMode.equals("Binary")) {
                if (!isBinary(input)) {
                    throw new IllegalArgumentException("Invalid binary input");
                }
                // For binary, unary operations might not make sense, but let's assume convert to decimal, apply, back to binary
                int dec = Integer.parseInt(input, 2);
                switch (op) {
                    case "sin":
                        result = model.sin(dec);
                        break;
                    case "cos":
                        result = model.cos(dec);
                        break;
                    case "sqrt":
                        result = model.sqrt(dec);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operation");
                }
                view.setDisplayText(Integer.toBinaryString((int) result));
            } else {
                double num = Double.parseDouble(input);
                switch (op) {
                    case "sin":
                        result = model.sin(num);
                        break;
                    case "cos":
                        result = model.cos(num);
                        break;
                    case "sqrt":
                        result = model.sqrt(num);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operation");
                }
                view.setDisplayText(String.valueOf(result));
            }
        } catch (NumberFormatException e) {
            view.showError("Invalid number format");
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        } catch (Exception e) {
            view.showError("An error occurred");
        }
    }

    class DigitListener implements ActionListener {

        private int digit;

        public DigitListener(int digit) {
            this.digit = digit;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentMode.equals("Binary") && digit > 1) return; // Only 0 and 1 in binary
            if (awaitingSecondNumber) {
                view.clearDisplay();
                awaitingSecondNumber = false;
            }
            view.appendToDisplay(String.valueOf(digit));
        }
    }

    class DotListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentMode.equals("Binary")) return; // No decimals in binary
            if (awaitingSecondNumber) {
                view.clearDisplay();
                awaitingSecondNumber = false;
            }
            String text = view.getDisplayText();
            if (!text.contains(".")) {
                view.appendToDisplay(".");
            }
        }
    }

    class OperationListener implements ActionListener {

        private String op;

        public OperationListener(String op) {
            this.op = op;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                firstNum = Double.parseDouble(view.getDisplayText());
                operation = op;
                awaitingSecondNumber = true;
            } catch (NumberFormatException ex) {
                view.showError("Invalid number");
            }
        }
    }

    class UnaryOperationListener implements ActionListener {

        private String op;

        public UnaryOperationListener(String op) {
            this.op = op;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            performUnaryCalculation(op);
        }
    }

    class EqualsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (operation != null) {
                performCalculation();
            }
        }
    }

    class ClearListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearDisplay();
            operation = null;
            awaitingSecondNumber = false;
        }
    }

    class ModeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            currentMode = view.getMode();
            view.clearDisplay();
            operation = null;
            awaitingSecondNumber = false;
        }
    }
}
