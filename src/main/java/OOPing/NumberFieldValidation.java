package OOPing;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NumberFieldValidation implements DocumentListener {
    final private String NUMBER_REGEX = "^[1-9]\\d*$";

    private JTextField field;
    private JButton startStopButton;

    public NumberFieldValidation(JTextField field, JButton startStopButton){
        this.field = field;
        this.startStopButton = startStopButton;

        field.getDocument().addDocumentListener(this);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String text = field.getText();

        if (text.matches(NUMBER_REGEX)) {
            startStopButton.setEnabled(true);
        } else {
            startStopButton.setEnabled(false);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String text = field.getText();

        if (text.matches(NUMBER_REGEX)) {
            startStopButton.setEnabled(true);
        } else {
            startStopButton.setEnabled(false);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        String text = field.getText();

        if (text.matches(NUMBER_REGEX)) {
            startStopButton.setEnabled(true);
        } else {
            startStopButton.setEnabled(false);
        }
    }
}
