package OOPing;

import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StrokeColorsPanel extends JPanel implements ActionListener {
    private JButton selectColorMagentaButton;
    private JButton selectColorBlueButton;
    private JButton selectColorBlackButton;
    private JButton selectColorPurpleButton;
    private JButton selectColorGrayButton;
    private JButton selectColorPinkButton;
    private JButton selectColorLighterPinkButton;
    private JButton selectColorYellowButton;
    private JButton selectColorGreenButton;

    private XYLineAndShapeRenderer renderer;

    private JButton customColorPickerButton;

    public StrokeColorsPanel() {
        setLayout(new GridLayout(2, 3));

        selectColorMagentaButton = new JButton();
        selectColorBlueButton = new JButton();
        selectColorBlackButton = new JButton();
        selectColorPurpleButton = new JButton();
        selectColorGrayButton = new JButton();
        selectColorPinkButton = new JButton();
        selectColorLighterPinkButton = new JButton();
        selectColorYellowButton = new JButton();
        selectColorGreenButton = new JButton();
        customColorPickerButton = new JButton();

        selectColorMagentaButton.addActionListener(this::actionPerformed);
        selectColorBlueButton.addActionListener(this::actionPerformed);
        selectColorBlackButton.addActionListener(this::actionPerformed);
        selectColorPurpleButton.addActionListener(this::actionPerformed);
        selectColorGrayButton.addActionListener(this::actionPerformed);
        selectColorPinkButton.addActionListener(this::actionPerformed);
        selectColorLighterPinkButton.addActionListener(this::actionPerformed);
        selectColorYellowButton.addActionListener(this::actionPerformed);
        selectColorGreenButton.addActionListener(this::actionPerformed);
        customColorPickerButton.addActionListener(this::actionPerformed);

        selectColorMagentaButton.setBackground(Color.magenta);
        selectColorBlueButton.setBackground(Color.blue);
        selectColorBlackButton.setBackground(Color.black);
        selectColorPurpleButton.setBackground(new Color(111, 24, 211));
        selectColorGrayButton.setBackground(Color.GRAY);
        selectColorPinkButton.setBackground(Color.PINK);
        selectColorLighterPinkButton.setBackground(new Color(248, 195, 245));
        selectColorYellowButton.setBackground(Color.yellow);
        selectColorGreenButton.setBackground(Color.green);

        add(selectColorMagentaButton);
        add(selectColorBlueButton);
        add(selectColorBlackButton);
        add(selectColorPurpleButton);
        add(selectColorGrayButton);
        add(selectColorPinkButton);
        add(selectColorLighterPinkButton);
        add(selectColorYellowButton);
        add(selectColorGreenButton);

        // Todo custom color picker
        customColorPickerButton.setText("Custom");
        add(customColorPickerButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectColorMagentaButton) {
            renderer.setSeriesPaint(0, Color.magenta);
        }

        if (e.getSource() == selectColorBlueButton) {
            renderer.setSeriesPaint(0, Color.BLUE);
        }

        if (e.getSource() == selectColorBlackButton) {
            renderer.setSeriesPaint(0, Color.BLACK);
        }

        if (e.getSource() == selectColorPurpleButton) {
            renderer.setSeriesPaint(0, new Color(111, 24, 211));
        }

        if (e.getSource() == selectColorGrayButton) {
            renderer.setSeriesPaint(0, Color.GRAY);
        }

        if (e.getSource() == selectColorPinkButton) {
            renderer.setSeriesPaint(0, Color.PINK);
        }

        if (e.getSource() == selectColorLighterPinkButton) {
            renderer.setSeriesPaint(0, new Color(248, 195, 245));
        }

        if (e.getSource() == selectColorYellowButton) {
            renderer.setSeriesPaint(0, Color.yellow);
        }

        if (e.getSource() == selectColorGreenButton) {
            renderer.setSeriesPaint(0, Color.green);
        }
    }

    public void setChartRenderer(XYLineAndShapeRenderer renderer) {
        this.renderer = renderer;
    }
}
