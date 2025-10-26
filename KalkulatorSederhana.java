import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.text.NumberFormat;
import java.util.Locale;
import java.math.BigDecimal;

public class KalkulatorSederhana extends JFrame implements ActionListener {
    private double num1 = 0;
    private char operator = '\0';
    private boolean isTypingNumber = false;
    private boolean justCalculated = false;

    private JTextField display;
    private JLabel historyDisplay;
    private JPanel buttonPanel;
    private JButton acButton;

    private final Color FRAME_BG = new Color(220, 240, 255);
    private final Color DISPLAY_BG = new Color(190, 230, 255);
    private final Color DISPLAY_TEXT_COLOR = new Color(20, 30, 60);
    private final Color HISTORY_TEXT_COLOR = new Color(0, 80, 150);
    private final Color BTN_SPECIAL_COLOR = new Color(0, 150, 255);
    private final Color BTN_NUM_COLOR = new Color(100, 200, 255);
    private final Color BTN_OP_COLOR = new Color(255, 30, 30);

    private NumberFormat numberParser;

    public KalkulatorSederhana() {
        setTitle("Kalkulator Kelompok 3 PBO");
        setSize(320, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(FRAME_BG);
        setLayout(new BorderLayout());

        numberParser = NumberFormat.getInstance(Locale.US);

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(DISPLAY_BG);
        displayPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 15, 5, 15),
                new LineBorder(new Color(150, 200, 230), 2)
        ));

        historyDisplay = new JLabel(" ");
        historyDisplay.setFont(new Font("Monospaced", Font.PLAIN, 16));
        historyDisplay.setHorizontalAlignment(JLabel.RIGHT);
        historyDisplay.setForeground(HISTORY_TEXT_COLOR);
        historyDisplay.setBorder(new EmptyBorder(5, 5, 0, 5));
        displayPanel.add(historyDisplay, BorderLayout.NORTH);

        display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Monospaced", Font.BOLD, 36));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(DISPLAY_BG);
        display.setForeground(DISPLAY_TEXT_COLOR);
        display.setBorder(new EmptyBorder(0, 5, 5, 5));
        displayPanel.add(display, BorderLayout.CENTER);

        add(displayPanel, BorderLayout.NORTH);

        buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(FRAME_BG);
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[][] buttonLabels = {
                {"AC", "Del", "+/-", "÷"}, 
                {"7", "8", "9", "×"},
                {"4", "5", "6", "−"},
                {"1", "2", "3", "+"},
                {"0", ",", "="}
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        for (int y = 0; y < buttonLabels.length; y++) {
            for (int x = 0; x < buttonLabels[y].length; x++) {
                String text = buttonLabels[y][x];
                gbc.gridx = x;
                gbc.gridy = y;
                gbc.gridwidth = 1;

                if (y == 0) { 
                    gbc.gridwidth = 1;
                    if (text.equals("AC")) gbc.gridx = 0;
                    if (text.equals("Del")) gbc.gridx = 1;
                    if (text.equals("+/-")) gbc.gridx = 2;
                    if (text.equals("÷")) gbc.gridx = 3;
                }
                else if (y == 4) { 
                    if (text.equals("0")) {
                        gbc.gridx = 0;
                        gbc.gridwidth = 1;
                    } else if (text.equals(",")) { 
                        gbc.gridx = 1;
                        gbc.gridwidth = 1;
                    } else if (text.equals("=")) { 
                        gbc.gridx = 2; 
                        gbc.gridwidth = 2; 
                    }
                }

                JButton button = createButton(text);

                if (text.equals("AC")) {
                    acButton = button;
                }

                buttonPanel.add(button, gbc);
            }
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text.replace(",", ".")); 
        
        Color bgColor;
        
        if (text.equals("AC") || text.equals("Del")) {
            bgColor = BTN_SPECIAL_COLOR;
        } else if ("+/-÷×−+=".contains(text)) { 
            bgColor = BTN_OP_COLOR; 
        } else {
            bgColor = BTN_NUM_COLOR;
        }

        button.setFont(new Font("Monospaced", Font.BOLD, 20));

        if (bgColor.equals(BTN_OP_COLOR) || bgColor.equals(BTN_SPECIAL_COLOR) || bgColor.equals(BTN_DEL_COLOR)) {
              button.setForeground(Color.WHITE);
        } else {
              button.setForeground(DISPLAY_TEXT_COLOR);
        }

        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor.darker().darker(), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        button.putClientProperty("JButton.buttonType", "roundRect");
        button.addActionListener(this);
        return button;
    }

    private double getDisplayValue() {
        try {
            String text = display.getText().replace(",", "."); 
            return numberParser.parse(text).doubleValue();
        } catch (java.text.ParseException e) {
            return 0;
        }
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return new BigDecimal(result).stripTrailingZeros().toPlainString();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals(",")) command = ".";

        switch (command) {
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
                handleNumberInput(command);
                break;
            case ".":
                handleDotInput();
                break;
            case "+/-":
                handleNegate();
                break;
            case "AC":
                resetCalculator();
                break;
            case "C":
                resetCalculator();
                break;
            case "Del":
                handleDelete();
                break;
            case "÷":
                handleOperator('/');
                break;
            case "×":
                handleOperator('*');
                break;
            case "−":
                handleOperator('-');
                break;
            case "+":
                handleOperator('+');
                break;
            case "=":
                handleEquals();
                break;
        }
    }

    private void handleDelete() {
        if (display.getText().equals("Error")) return;

        String currentText = display.getText();
        
        if (currentText.length() > 0 && isTypingNumber) {
            String newText = currentText.substring(0, currentText.length() - 1);

            if (newText.isEmpty() || newText.equals("-")) {
                display.setText("0");
            } else {
                display.setText(newText);
            }
        }
    }

    private void handleNumberInput(String number) {
        if (display.getText().equals("Error")) {
            display.setText("0");
        }
        
        if (justCalculated || !isTypingNumber) {
            display.setText(number);
            justCalculated = false;
        } else if (display.getText().equals("0")) {
            display.setText(number);
        } else {
            display.setText(display.getText() + number);
        }
        isTypingNumber = true;
    }

    private void handleDotInput() {
        if (display.getText().equals("Error")) {
            display.setText("0");
        }
        
        if (justCalculated || !isTypingNumber) {
            display.setText("0.");
            justCalculated = false;
        }
        else if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
        isTypingNumber = true;
    }

    private void resetCalculator() {
        display.setText("0");
        historyDisplay.setText(" ");
        num1 = 0;
        operator = '\0';
        isTypingNumber = false;
        justCalculated = false;
        acButton.setText("AC");
    }

    private void handleNegate() {
        if (display.getText().equals("0") || display.getText().equals("Error")) return;
        
        double val = getDisplayValue() * -1;
        display.setText(formatResult(val));
        
        if (justCalculated) {
            num1 = val;
        }
    }

    private double calculate(double n1, double n2, char op) throws ArithmeticException {
        switch (op) {
            case '+': return n1 + n2;
            case '-': return n1 - n2;
            case '*': return n1 * n2;
            case '/':
                if (n2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return n1 / n2;
            default:
                return n2;
        }
    }

    private void handleOperator(char op) {
        if (display.getText().equals("Error")) {
            operator = op;
            isTypingNumber = false;
            return;
        }
        
        if (operator != '\0' && isTypingNumber) {
            handleEquals();
            num1 = getDisplayValue(); 
        } 
        else if (operator == '\0') {
             num1 = getDisplayValue();
        }
        
        operator = op;
        isTypingNumber = false;
        justCalculated = false; 
        
        char displayOp = op;
        if (op == '/') displayOp = '÷';
        if (op == '*') displayOp = '×';
        if (op == '-') displayOp = '−';
        historyDisplay.setText(formatResult(num1) + " " + displayOp + " ");
        
        acButton.setText("AC"); 
    }

    private void handleEquals() {
        if (operator != '\0') {
            double num2;
            
            if (!isTypingNumber && !justCalculated) {
                num2 = num1; 
            } else {
                num2 = getDisplayValue();
            }

            char displayOp = operator;
            if (operator == '/') displayOp = '÷';
            if (operator == '*') displayOp = '×';
            if (operator == '-') displayOp = '−';
            
            historyDisplay.setText(formatResult(num1) + " " + displayOp + " " + formatResult(num2) + " =");

            try {
                double result = calculate(num1, num2, operator);
                display.setText(formatResult(result));
                num1 = result; 
            } catch (ArithmeticException ex) {
                display.setText("Error");
                num1 = 0;
            }

            operator = '\0';
            isTypingNumber = false;
            justCalculated = true;
            acButton.setText("AC"); 
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error terjadi karena " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            new KalkulatorSederhana().setVisible(true);
        });
    }
}