package OOPing;

import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    private JLabel title;

    private JButton startStopButton;

    private StrokeColorsPanel strokeColorsPanel;

    private XYLineAndShapeRenderer renderer;

    public ButtonsPanel(){
        setLayout(new GridLayout(10, 1));

        strokeColorsPanel = new StrokeColorsPanel();
        title = new JLabel();
        startStopButton = new JButton("Start button");

        title.setText("Here will be configuration buttons and stuff");

        title.setHorizontalAlignment(SwingConstants.CENTER);



        add(title);
        add(startStopButton);

        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());

        add(strokeColorsPanel);
    }

    public void setChartRenderer(XYLineAndShapeRenderer renderer){
        this.renderer = renderer;
        strokeColorsPanel.setChartRenderer(renderer);
    }
}
