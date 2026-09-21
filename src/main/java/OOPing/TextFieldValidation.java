package OOPing;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextFieldValidation implements DocumentListener {
    final private String URL_REGEX = "^(https?://)?[\\w.-]+(?:\\.[\\w.-]+)+(?:[/\\w .-]*)*/?$";

    private JTextField field;
    private JButton startStopButton;

    public TextFieldValidation(JTextField field, JButton startStopButton){
        this.field = field;
        this.startStopButton = startStopButton;

        field.getDocument().addDocumentListener(this);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String text = field.getText();

        if (text.matches(URL_REGEX)) {
            startStopButton.setEnabled(true);
        } else {
            startStopButton.setEnabled(false);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String text = field.getText();

        if (text.matches(URL_REGEX)) {
            startStopButton.setEnabled(true);
        } else {
            startStopButton.setEnabled(false);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        String text = field.getText();

        if (text.matches(URL_REGEX)) {
            startStopButton.setEnabled(true);
        } else {
            startStopButton.setEnabled(false);
        }
    }
}
