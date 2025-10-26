import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class KalkulatorSederhana implements ActionListener {

    // --- 1. KONSTANTA UI & WARNA ---
    private static final int FRAME_WIDTH = 420;
    private static final int FRAME_HEIGHT = 600;
    private static final Color BACKGROUND_COLOR = new Color(30, 30, 30);
    private static final Color DISPLAY_COLOR = new Color(50, 50, 50);
    private static final Color TEXT_DISPLAY_COLOR = Color.WHITE;
    private static final Color NUMBER_BUTTON_COLOR = new Color(70, 70, 70);
    private static final Color OPERATOR_BUTTON_COLOR = new Color(255, 160, 0); // Oranye
    private static final Color UTILITY_BUTTON_COLOR = new Color(100, 100, 100); // Abu-abu terang
    private static final Color EQUAL_BUTTON_COLOR = new Color(0, 150, 200);     // Biru

    // --- 2. KOMPONEN UI ---
    private final JFrame frame = new JFrame("Kalkulator Keren Kelompok 3 PBO");
    private final JTextField displayField = new JTextField();
    private final JButton[] numberButtons = new JButton[10];
    private final JPanel panel = new JPanel();

    // Tombol fungsi
    private final JButton addButton = new JButton("+");
    private final JButton subButton = new JButton("-");
    private final JButton mulButton = new JButton("×"); // Simbol kali
    private final JButton divButton = new JButton("÷"); // Simbol bagi
    private final JButton decButton = new JButton(".");
    private final JButton equButton = new JButton("=");
    private final JButton delButton = new JButton("DEL");
    private final JButton clrButton = new JButton("AC");
    private final JButton negButton = new JButton("±");

    // --- 3. VARIABEL LOGIKA ---
    private double num1 = 0.0;
    private double num2 = 0.0;
    private double result = 0.0;
    private char operator;

    // --- 4. KONSTRUKTOR ---
    KalkulatorSederhana() {
        setupFrame();
        setupDisplay();
        setupButtons();
        setupPanel();
        frame.setVisible(true);
    }

    private void setupFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(null);
        frame.getContentPane().setBackground(BACKGROUND_COLOR);
        frame.setLocationRelativeTo(null);
    }

    private void setupDisplay() {
        displayField.setBounds(30, 40, 360, 80);
        displayField.setFont(new Font("Monospaced", Font.BOLD, 48));
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setBackground(DISPLAY_COLOR);
        displayField.setForeground(TEXT_DISPLAY_COLOR);
        displayField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(displayField);
    }

    private void setupButtons() {
        JButton[] functions = {clrButton, negButton, delButton, divButton, mulButton, subButton, addButton, decButton, equButton};
        ActionListener buttonListener = this;

        // 1. Tombol Angka
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = createStyledButton(String.valueOf(i), NUMBER_BUTTON_COLOR, TEXT_DISPLAY_COLOR, buttonListener);
        }

        // 2. Tombol Fungsi
        for (JButton b : functions) {
            Color btnColor = NUMBER_BUTTON_COLOR;
            Color txtColor = TEXT_DISPLAY_COLOR;

            if (b == divButton || b == mulButton || b == subButton || b == addButton) {
                btnColor = OPERATOR_BUTTON_COLOR;
            } else if (b == clrButton || b == delButton || b == negButton) {
                btnColor = UTILITY_BUTTON_COLOR;
                txtColor = Color.BLACK;
            } else if (b == equButton) {
                btnColor = EQUAL_BUTTON_COLOR;
            }

            b.setBackground(btnColor);
            b.setForeground(txtColor);
            b.setFont(new Font("Monospaced", Font.BOLD, 22));
            b.setFocusable(false);
            b.setBorder(BorderFactory.createLineBorder(BACKGROUND_COLOR, 1));
            b.addActionListener(buttonListener);
        }
    }

    private JButton createStyledButton(String text, Color bg, Color fg, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFont(new Font("Monospaced", Font.BOLD, 22));
        button.setFocusable(false);
        button.setBorder(BorderFactory.createLineBorder(BACKGROUND_COLOR, 1));
        button.addActionListener(listener);
        return button;
    }

    private void setupPanel() {
        panel.setBounds(30, 140, 360, 400);
        panel.setLayout(new GridLayout(5, 4, 1, 1));
        panel.setBackground(BACKGROUND_COLOR);

        // BARIS 1 (Utility & Operator, TETAP)
        panel.add(clrButton);
        panel.add(negButton);
        panel.add(delButton);
        panel.add(divButton);

        // BARIS 2 (1, 2, 3) - Keypad Telepon
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(mulButton);

        // BARIS 3 (4, 5, 6, TETAP)
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        // BARIS 4 (7, 8, 9) - Keypad Telepon
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(addButton);

        // BARIS 5 (0, Titik, Spacer, Sama Dengan, TETAP)
        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(new JLabel(""));
        panel.add(equButton);

        frame.add(panel);
    }

    // --- 5. LOGIKA ACTION PERFORMED ---
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String currentText = displayField.getText();

        // Tombol Angka dan Titik
        for (int i = 0; i < 10; i++) {
            if (source == numberButtons[i]) {
                displayField.setText(currentText.concat(String.valueOf(i)));
                return;
            }
        }
        if (source == decButton && !currentText.contains(".")) {
            displayField.setText(currentText.concat("."));
            return;
        }

        // Tombol Operator
        if (source == addButton || source == subButton || source == mulButton || source == divButton) {
            if (currentText.isEmpty() || currentText.contains("Error")) {
                return;
            }
            try {
                num1 = Double.parseDouble(currentText);
                if (source == addButton) operator = '+';
                else if (source == subButton) operator = '-';
                else if (source == mulButton) operator = '*';
                else if (source == divButton) operator = '/';
                displayField.setText("");
            } catch (NumberFormatException ex) {
                displayField.setText("Error");
                num1 = 0.0;
            }
            return;
        }

        // Tombol Equals
        if (source == equButton) {
            if (currentText.isEmpty() || currentText.contains("Error")) return;

            try {
                num2 = Double.parseDouble(currentText);

                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 == 0.0) {
                            displayField.setText("Error: Div by Zero");
                            num1 = 0.0;
                            return;
                        }
                        result = num1 / num2;
                        break;
                    default:
                        result = num2;
                }

                // Tampilkan hasil (hilangkan .0 jika bilangan bulat)
                displayField.setText((result == Math.floor(result) && !Double.isInfinite(result)) ?
                        String.valueOf((long) result) :
                        String.valueOf(result));
                num1 = result;
                operator = '\u0000';
            } catch (Exception ex) {
                displayField.setText("Error");
                num1 = 0.0;
            }
            return;
        }

        // Tombol Clear (AC)
        if (source == clrButton) {
            displayField.setText("");
            num1 = num2 = result = 0.0;
            operator = '\u0000';
            return;
        }

        // Tombol Delete (DEL)
        if (source == delButton && currentText.length() > 0) {
            displayField.setText(currentText.substring(0, currentText.length() - 1));
            return;
        }

        // Tombol Negate (±)
        if (source == negButton) {
            try {
                double value = Double.parseDouble(currentText);
                displayField.setText(String.valueOf(value * -1.0));
            } catch (NumberFormatException ignored) {
                // Abaikan
            }
        }
    }

    // --- 6. MAIN METHOD ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(KalkulatorSederhana::new);
    }
}