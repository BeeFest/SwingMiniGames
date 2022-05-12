package beefest.swing.minigames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Lottery extends JFrame implements ActionListener, FocusListener {
    int number, number1, number2;
    JLabel display = new JLabel("Fill in the number you want to play the lottery with: ");
    JTextArea input = new JTextArea();
    JTextArea output = new JTextArea();
    JPanel panel = new JPanel();
    JButton button = new JButton("Start lottery");

    public Lottery() {
        display.setFont(display.getFont().deriveFont(15.0f));
        getContentPane().add(display, BorderLayout.NORTH);

        panel.setLayout(new GridLayout(1, 2));
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.add(input);
        input.setLineWrap(true);
        input.setFont(input.getFont().deriveFont(80.0f));
        input.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        input.addFocusListener(this);

        panel.add(output);
        output.setLineWrap(true);
        output.setFont(output.getFont().deriveFont(Font.BOLD, 15.0f));
        output.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        getContentPane().add(button, BorderLayout.SOUTH);
        button.addActionListener(this);
    }

    public static void main(String[] args) {
        Lottery frame = new Lottery();
        frame.setSize(600, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void generateNumbers() {
        number = (int) (100 * Math.random());
        number1 = number % 10;
        number2 = number / 10;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        output.setText("");
        display.setText("");
        generateNumbers();
        System.out.println(number);
        StringBuilder sb = new StringBuilder();

        try {
        if (Integer.parseInt(input.getText()) < 0 || Integer.parseInt(input.getText()) > 99) {
            display.setText("Invalid input. Try a number between 0 and 99: ");
            throw new IllegalArgumentException();
        }

        if (Integer.parseInt(input.getText()) < 10) {
            sb.append(0);
            sb.append(input.getText());
        } else sb.append(input.getText());

        if (Integer.parseInt(sb.toString()) == number) {
            output.setText("\n\nCongratulations, you won \nthe jackpot of € 10.000!");
            display.setText("Winning number was " + number + ". Want to play again?");
            generateNumbers();
        } else if ((Integer.parseInt(String.valueOf(sb.toString().charAt(0))) == (number1) ||
                Integer.parseInt(String.valueOf(sb.toString().charAt(0))) == (number2)) &&
                (Integer.parseInt(String.valueOf(sb.toString().charAt(1))) == (number1) ||
                        Integer.parseInt(String.valueOf(sb.toString().charAt(1))) == (number2))) {
            output.setText("\n\nCongratulations, you won \na prize of € 3.000!");
            display.setText("Winning number was " + number + ". Want to play again?");
            generateNumbers();
        } else if (Integer.parseInt(String.valueOf(sb.toString().charAt(0))) == (number1) ||
                Integer.parseInt(String.valueOf(sb.toString().charAt(0))) == (number2) ||
                Integer.parseInt(String.valueOf(sb.toString().charAt(1))) == (number1) ||
                Integer.parseInt(String.valueOf(sb.toString().charAt(1))) == (number2)) {
            output.setText("\n\nCongratulations, you won \na prize of € 1.000!");
            display.setText("Winning number was " + number + ". Want to play again?");
            generateNumbers();
        } else {
            output.setText("\n\nNo prizes won. Better luck next time.");
            display.setText("Winning number was " + number + ". Want to play again?");
        }

    } catch(
    NumberFormatException exc)
    {
        display.setText("Invalid input. Try a number between 0 and 99: ");
        throw new NumberFormatException();
    }
}
    @Override
    public void focusGained(FocusEvent e) {
        input.setText("");
    }
    @Override
    public void focusLost(FocusEvent e) {
    }
}