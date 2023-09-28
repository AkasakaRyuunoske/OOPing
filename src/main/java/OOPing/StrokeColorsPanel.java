package OOPing;

import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StrokeColorsPanel extends JPanel implements ActionListener {
    private JButton color_1_button;
    private JButton color_2_button;
    private JButton color_3_button;
    private JButton color_4_button;
    private JButton color_5_button;
    private JButton color_6_button;
    private JButton color_7_button;
    private JButton color_8_button;
    private JButton color_9_button;

    private JButton stopButton;
    private XYLineAndShapeRenderer renderer;

    public StrokeColorsPanel() {
        setLayout(new GridLayout(3, 3));

        color_1_button = new JButton();
        color_2_button = new JButton();
        color_3_button = new JButton();
        color_4_button = new JButton();
        color_5_button = new JButton();
        color_6_button = new JButton();
        color_7_button = new JButton();
        color_8_button = new JButton();
        color_9_button = new JButton();


        color_1_button.addActionListener(this::actionPerformed);
        color_2_button.addActionListener(this::actionPerformed);
        color_3_button.addActionListener(this::actionPerformed);
        color_4_button.addActionListener(this::actionPerformed);
        color_5_button.addActionListener(this::actionPerformed);
        color_6_button.addActionListener(this::actionPerformed);
        color_7_button.addActionListener(this::actionPerformed);
        color_8_button.addActionListener(this::actionPerformed);
        color_9_button.addActionListener(this::actionPerformed);

        color_1_button.setBackground(Color.magenta);
        color_2_button.setBackground(Color.blue);
        color_3_button.setBackground(Color.black);
        color_4_button.setBackground(new Color(111, 24, 211));
        color_5_button.setBackground(Color.GRAY);
        color_6_button.setBackground(Color.PINK);
        color_7_button.setBackground(new Color(248, 195, 245));
//        color_8_button.setBackground(Color.blue);
//        color_9_button.setBackground(Color.black);

        add(color_1_button);
        add(color_2_button);
        add(color_3_button);
        add(color_4_button);
        add(color_5_button);
        add(color_6_button);
        add(color_7_button);
        add(color_8_button);
        add(color_9_button);

        stopButton = new JButton();
        stopButton.setText("Stop");

        add(stopButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == color_1_button){
            System.out.println("color 1 selected");
            renderer.setSeriesPaint(0, Color.magenta);
        }

        if (e.getSource() == color_2_button){
            System.out.println("color 2 selected");
            renderer.setSeriesPaint(0, Color.BLUE);
        }

        if (e.getSource() == color_3_button){
            System.out.println("color 3 selected");
            renderer.setSeriesPaint(0, Color.BLACK);
        }

        if (e.getSource() == color_4_button){
            System.out.println("color 4 selected");
            renderer.setSeriesPaint(0, new Color(111, 24, 211));
        }

        if (e.getSource() == color_5_button){
            System.out.println("color 5 selected");
            renderer.setSeriesPaint(0, Color.GRAY);
        }

        if (e.getSource() == color_6_button){
            System.out.println("color 6 selected");
            renderer.setSeriesPaint(0, Color.PINK);
        }

        if (e.getSource() == color_7_button){
            System.out.println("color 7 selected");
            renderer.setSeriesPaint(0, new Color(248, 195, 245));
        }

        if (e.getSource() == color_8_button){
            System.out.println("color 8 selected");
            renderer.setSeriesPaint(0, Color.BLACK);
        }

        if (e.getSource() == color_9_button){
            System.out.println("color 9 selected");
            renderer.setSeriesPaint(0, Color.BLACK);
        }

        if (e.getSource() == stopButton) {
            System.out.println("Stopping...");
        }
    }

    public void setChartRenderer(XYLineAndShapeRenderer renderer){
        this.renderer = renderer;
    }
}
