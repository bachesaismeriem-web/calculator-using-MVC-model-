
public class CalculatorModel {

    // ===== SIMPLE =====
    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }

    // ===== SCIENTIFIC =====
    public double sin(double x) {
        return Math.sin(x);
    }

    public double cos(double x) {
        return Math.cos(x);
    }

    public double sqrt(double x) {
        return Math.sqrt(x);
    }

    // ===== BINARY =====
    private int toDecimal(String bin) {
        return Integer.parseInt(bin, 2);
    }

    private String toBinary(int dec) {
        return Integer.toBinaryString(dec);
    }

    public String addBinary(String a, String b) {
        return toBinary(toDecimal(a) + toDecimal(b));
    }

    public String subBinary(String a, String b) {
        return toBinary(toDecimal(a) - toDecimal(b));
    }

    public String mulBinary(String a, String b) {
        return toBinary(toDecimal(a) * toDecimal(b));
    }

    public String divBinary(String a, String b) {
        int divisor = toDecimal(b);
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return toBinary(toDecimal(a) / divisor);
    }
}
