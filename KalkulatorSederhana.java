package Praktikum8;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class KalkulatorSederhana implements ActionListener {

    JFrame frame;
    JTextField displayField; 
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];

    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    KalkulatorSederhana() {
        // Frame dengan warna krem lembut seperti kalkulator pada gambar
        frame = new JFrame("Kalkulator Kelompok 3 PBO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(230, 220, 215)); // krem muda
        frame.setLocationRelativeTo(null); 

        // Bidang tampilan (layar)
        displayField = new JTextField();
        displayField.setBounds(50, 25, 300, 50);
        displayField.setFont(new Font("Arial", Font.PLAIN, 40));
        displayField.setEditable(false); 
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setBackground(new Color(210, 205, 200)); // abu kehijauan lembut seperti layar kalkulator
        displayField.setForeground(new Color(70, 70, 60)); // teks agak gelap
        displayField.setBorder(BorderFactory.createLineBorder(new Color(200, 190, 185)));

        // Tombol fungsi
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("CE");
        negButton = new JButton("+/-");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        // Gaya tombol fungsi pastel
        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            functionButtons[i].setFocusable(false);
            functionButtons[i].setBorder(BorderFactory.createLineBorder(new Color(190, 180, 175)));

            if (functionButtons[i] == clrButton) { 
                functionButtons[i].setBackground(new Color(190, 100, 90)); // merah bata lembut (CE)
                functionButtons[i].setForeground(Color.WHITE);
            } 
            else if (functionButtons[i] == delButton) {
                functionButtons[i].setBackground(new Color(195, 150, 120)); // cokelat muda (ON)
                functionButtons[i].setForeground(Color.WHITE);
            } 
            else {
                functionButtons[i].setBackground(new Color(230, 225, 220)); // abu pastel lembut
                functionButtons[i].setForeground(new Color(60, 60, 60));
            }
        }

        // Tombol angka (warna abu muda)
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(new Color(235, 235, 230)); // abu putih lembut
            numberButtons[i].setForeground(new Color(50, 50, 50));
            numberButtons[i].setBorder(BorderFactory.createLineBorder(new Color(200, 190, 180)));
        }

        // Tombol "=" warna abu ke hijau lembut
        equButton.setBackground(new Color(215, 215, 200));
        equButton.setForeground(new Color(50, 50, 50));

        // Panel tombol
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 350);
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setBackground(new Color(230, 220, 215));

        // Susunan tombol
        panel.add(clrButton);
        panel.add(delButton);
        panel.add(negButton);
        panel.add(divButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(new JLabel(""));

        frame.add(panel);
        frame.add(displayField);
        frame.setVisible(true); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                displayField.setText(displayField.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton && !displayField.getText().contains(".")) {
            displayField.setText(displayField.getText().concat("."));
        }

        if (e.getSource() == addButton || e.getSource() == subButton || e.getSource() == mulButton || e.getSource() == divButton) {
            try {
                num1 = Double.parseDouble(displayField.getText());
                if (e.getSource() == addButton) operator = '+';
                if (e.getSource() == subButton) operator = '-';
                if (e.getSource() == mulButton) operator = '*';
                if (e.getSource() == divButton) operator = '/';
                displayField.setText("");
            } catch (NumberFormatException ex) {
                displayField.setText("Error");
                num1 = 0;
            }
        }

        if (e.getSource() == equButton) {
            try {
                if (displayField.getText().isEmpty()) return;
                num2 = Double.parseDouble(displayField.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 == 0) {
                            displayField.setText("Error: Div by Zero");
                            num1 = 0; return;
                        }
                        result = num1 / num2;
                        break;
                }
                displayField.setText(String.valueOf(result));
                num1 = result;
            } catch (Exception ex) {
                displayField.setText("Error");
                num1 = 0;
            }
        }

        if (e.getSource() == clrButton) {
            displayField.setText("");
            num1 = 0; num2 = 0; result = 0;
        }

        if (e.getSource() == delButton) {
            String string = displayField.getText();
            if (string.length() > 0) {
                 displayField.setText(string.substring(0, string.length() - 1));
            }
        }

        if (e.getSource() == negButton) {
            try {
                double temp = Double.parseDouble(displayField.getText());
                temp *= -1;
                displayField.setText(String.valueOf(temp));
            } catch (NumberFormatException ex) {
                // abaikan
            }
        }
    }

    public static void main(String[] args) {
        new KalkulatorSederhana();
    }
}
