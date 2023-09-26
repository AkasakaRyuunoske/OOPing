package OOPing;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    JLabel title;

    JButton startStopButton;
    public ButtonsPanel(){
        setLayout(new GridLayout(10, 1));
        title = new JLabel();
        startStopButton = new JButton("Start button");

        title.setText("Here will be configuration buttons and stuff");

        title.setHorizontalAlignment(SwingConstants.CENTER);

        add(title);
        add(startStopButton);
    }
}
