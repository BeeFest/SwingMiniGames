package beefest.swing.minigames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GuessTheNumber extends JFrame implements ActionListener, FocusListener {
    int number = 1 + (int) (100 * Math.random());
    JLabel display = new JLabel("I have a number in mind. Can you guess it?");
    JTextArea input = new JTextArea();
    JTextArea output = new JTextArea();
    JPanel panel = new JPanel();
    JButton button = new JButton("Guess");

    public GuessTheNumber() {
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
        GuessTheNumber frame = new GuessTheNumber();
        frame.setSize(600, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        output.setText("");
        display.setText("");
        try{
        int guess = Integer.parseInt(input.getText());
        if (guess == number) {
            output.setText("\n\nCongratulations, you guessed correctly!");
            number = 1 + (int) (100 * Math.random());
            display.setText("I got a new number in mind. Want to play again?");
        } else if (guess < number) {
            output.setText("\n\nGuess higher!");
        } else if (guess > number) {
            output.setText("\n\nGuess lower!");
        }
    }catch (NumberFormatException exc){
            display.setText("Invalid input. Try a number between 1 and 100: ");
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