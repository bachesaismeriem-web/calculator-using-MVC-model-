
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CalculatorView extends JFrame {

    JTextField display = new JTextField(20);
    JButton[] digitButtons = new JButton[10];
    JButton dotButton = new JButton(".");
    JButton addButton = new JButton("+");
    JButton subButton = new JButton("-");
    JButton mulButton = new JButton("*");
    JButton divButton = new JButton("/");
    JButton sinButton = new JButton("sin");
    JButton cosButton = new JButton("cos");
    JButton sqrtButton = new JButton("sqrt");
    JButton equalsButton = new JButton("=");
    JButton clearButton = new JButton("C");
    JComboBox<String> modeCombo = new JComboBox<>(new String[]{"Simple", "Binary", "Scientific"});

    public CalculatorView() {
        setTitle("Calculator");
        setLayout(new BorderLayout());

        // Display
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        // Mode selector
        JPanel modePanel = new JPanel();
        modePanel.add(new JLabel("Mode:"));
        modePanel.add(modeCombo);
        add(modePanel, BorderLayout.SOUTH);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));

        // Row 1: 7 8 9 /
        buttonPanel.add(createDigitButton("7"));
        buttonPanel.add(createDigitButton("8"));
        buttonPanel.add(createDigitButton("9"));
        buttonPanel.add(divButton);

        // Row 2: 4 5 6 *
        buttonPanel.add(createDigitButton("4"));
        buttonPanel.add(createDigitButton("5"));
        buttonPanel.add(createDigitButton("6"));
        buttonPanel.add(mulButton);

        // Row 3: 1 2 3 -
        buttonPanel.add(createDigitButton("1"));
        buttonPanel.add(createDigitButton("2"));
        buttonPanel.add(createDigitButton("3"));
        buttonPanel.add(subButton);

        // Row 4: 0 . = +
        buttonPanel.add(createDigitButton("0"));
        buttonPanel.add(dotButton);
        buttonPanel.add(equalsButton);
        buttonPanel.add(addButton);

        // Row 5: sin cos sqrt C
        buttonPanel.add(sinButton);
        buttonPanel.add(cosButton);
        buttonPanel.add(sqrtButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.CENTER);

        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Styling - Feminine color scheme with light pink
        Color lightPink = new Color(255, 192, 203);
        Color rose = new Color(255, 160, 180);
        Color paleViolet = new Color(230, 190, 220);
        Color lavender = new Color(230, 210, 240);
        Color paleGreen = new Color(200, 240, 200);

        getContentPane().setBackground(lightPink);
        display.setBackground(new Color(255, 250, 250)); // Floral white
        display.setForeground(new Color(100, 50, 100)); // Deep plum

        // Set panel backgrounds
        modePanel.setBackground(lightPink);

        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        Font labelFont = new Font("Arial", Font.BOLD, 12);

        // Style digit buttons - light lavender
        for (JButton b : digitButtons) {
            if (b != null) {
                b.setBackground(lavender);
                b.setForeground(new Color(100, 50, 100));
                b.setFont(buttonFont);
                b.setFocusPainted(false);
            }
        }
        dotButton.setBackground(lavender);
        dotButton.setForeground(new Color(100, 50, 100));
        dotButton.setFont(buttonFont);
        dotButton.setFocusPainted(false);

        // Style operation buttons - pale violet
        addButton.setBackground(paleViolet);
        subButton.setBackground(paleViolet);
        mulButton.setBackground(paleViolet);
        divButton.setBackground(paleViolet);

        // Style scientific buttons - pale blue (lightened cyan)
        Color paleBlue = new Color(200, 240, 250);
        sinButton.setBackground(paleBlue);
        cosButton.setBackground(paleBlue);
        sqrtButton.setBackground(paleBlue);

        // Style equals button - pale green
        equalsButton.setBackground(paleGreen);

        // Style clear button - pale rose
        clearButton.setBackground(rose);

        // Set fonts and appearance for all operation buttons
        Color textColor = new Color(100, 50, 100);
        addButton.setForeground(textColor);
        subButton.setForeground(textColor);
        mulButton.setForeground(textColor);
        divButton.setForeground(textColor);
        sinButton.setForeground(textColor);
        cosButton.setForeground(textColor);
        sqrtButton.setForeground(textColor);
        equalsButton.setForeground(textColor);
        clearButton.setForeground(textColor);

        addButton.setFont(buttonFont);
        subButton.setFont(buttonFont);
        mulButton.setFont(buttonFont);
        divButton.setFont(buttonFont);
        sinButton.setFont(buttonFont);
        cosButton.setFont(buttonFont);
        sqrtButton.setFont(buttonFont);
        equalsButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);

        addButton.setFocusPainted(false);
        subButton.setFocusPainted(false);
        mulButton.setFocusPainted(false);
        divButton.setFocusPainted(false);
        sinButton.setFocusPainted(false);
        cosButton.setFocusPainted(false);
        sqrtButton.setFocusPainted(false);
        equalsButton.setFocusPainted(false);
        clearButton.setFocusPainted(false);

        // Style mode selector
        JLabel modeLabel = (JLabel) modePanel.getComponent(0);
        modeLabel.setFont(labelFont);
        modeLabel.setForeground(new Color(100, 50, 100));
        modeCombo.setBackground(lavender);
        modeCombo.setForeground(new Color(100, 50, 100));
    }

    private JButton createDigitButton(String text) {
        JButton button = new JButton(text);
        digitButtons[Integer.parseInt(text)] = button;
        return button;
    }

    // Getters and setters
    public String getDisplayText() {
        return display.getText();
    }

    public void setDisplayText(String text) {
        display.setText(text);
    }

    public void appendToDisplay(String text) {
        display.setText(display.getText() + text);
    }

    public void clearDisplay() {
        display.setText("");
    }

    public String getMode() {
        return (String) modeCombo.getSelectedItem();
    }

    // Listeners
    public void addDigitListener(ActionListener l, int digit) {
        digitButtons[digit].addActionListener(l);
    }

    public void addDotListener(ActionListener l) {
        dotButton.addActionListener(l);
    }

    public void addAddListener(ActionListener l) {
        addButton.addActionListener(l);
    }

    public void addSubListener(ActionListener l) {
        subButton.addActionListener(l);
    }

    public void addMulListener(ActionListener l) {
        mulButton.addActionListener(l);
    }

    public void addDivListener(ActionListener l) {
        divButton.addActionListener(l);
    }

    public void addSinListener(ActionListener l) {
        sinButton.addActionListener(l);
    }

    public void addCosListener(ActionListener l) {
        cosButton.addActionListener(l);
    }

    public void addSqrtListener(ActionListener l) {
        sqrtButton.addActionListener(l);
    }

    public void addEqualsListener(ActionListener l) {
        equalsButton.addActionListener(l);
    }

    public void addClearListener(ActionListener l) {
        clearButton.addActionListener(l);
    }

    public void addModeListener(ActionListener l) {
        modeCombo.addActionListener(l);
    }

    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
