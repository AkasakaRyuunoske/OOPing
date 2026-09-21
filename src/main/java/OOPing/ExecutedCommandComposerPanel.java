package OOPing;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExecutedCommandComposerPanel extends JPanel implements ActionListener {
    private JTextField urlToPingTextField;
    private JTextField pingCommandTextField;
    private JTextField pingCountTextField;

    private JButton startStopButton;

    public ExecutedCommandComposerPanel(JButton startStopButton){
        this.startStopButton = startStopButton;

        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        // Ping Command Text Field:
        pingCommandTextField = new JTextField();
        pingCommandTextField.setText("C:\\Windows\\System32\\ping.exe");
        pingCommandTextField.setEnabled(false);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;

        add(pingCommandTextField, gridBagConstraints);

        // Url to Ping Text Field:
        urlToPingTextField = new JTextField();
        urlToPingTextField.setText("www.youtube.com");
        urlToPingTextField.getDocument().addDocumentListener(new TextFieldValidation(urlToPingTextField, startStopButton));

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;

        add(urlToPingTextField, gridBagConstraints);

        // Ping Count Text Field:
        pingCountTextField = new JTextField();
        pingCountTextField.addActionListener(this::actionPerformed);
        pingCountTextField.getDocument().addDocumentListener(new NumberFieldValidation(pingCountTextField, startStopButton));
        pingCountTextField.setText("200");

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;

        add(pingCountTextField, gridBagConstraints);
    }

    public String[] buildCommandFromInput(){
        String[] commands = {"C:\\Windows\\System32\\ping.exe", "www.youtube.com", "-n", "200000"};

        return commands;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
