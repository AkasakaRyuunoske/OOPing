package OOPing;

import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsPanel extends JPanel implements ActionListener {
    private JLabel title;

    private JButton startStopButton;

    private StrokeColorsPanel strokeColorsPanel;

    private XYLineAndShapeRenderer renderer;

    private LogsPanel logsPanel;

    public ButtonsPanel(LogsPanel logsPanel){
        this.logsPanel = logsPanel;

        setLayout(new GridLayout(10, 1));

        strokeColorsPanel = new StrokeColorsPanel();
        title = new JLabel();
        startStopButton = new JButton("Start OOPing");
        startStopButton.setFocusable(false);
        startStopButton.addActionListener(this::actionPerformed);
        title.setText("Here will be configuration buttons and stuff");

        title.setHorizontalAlignment(SwingConstants.CENTER);

        add(title);
        add(startStopButton);

        // Acts as a <br><br><br><br> sequence. Will be replaced once this space is occupied with something useful
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startStopButton) {
            logsPanel.redirectCLIOutput();
        }
    }
}
