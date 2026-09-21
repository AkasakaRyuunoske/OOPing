package OOPing;

import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsPanel extends JPanel implements ActionListener {
    private JLabel title;

    private final JButton startStopButton;

    private ExecutedCommandComposerPanel executedCommandComposerPanel;

    private JLabel strokeColorsLabel;

    private final StrokeColorsPanel strokeColorsPanel;

    private XYLineAndShapeRenderer renderer;

    private final LogsPanel logsPanel;

    final private Font componentsDescriptionLabelsFont = new Font("Serif", Font.BOLD, 18);

    public ButtonsPanel(LogsPanel logsPanel) {
        this.logsPanel = logsPanel;

        setLayout(new GridLayout(10, 1));

        strokeColorsPanel = new StrokeColorsPanel();
        title = new JLabel();
        startStopButton = new JButton("Start OOPing");
        startStopButton.setFont(componentsDescriptionLabelsFont);
        startStopButton.setFocusable(false);
        startStopButton.addActionListener(this::actionPerformed);
        title.setText("Here will be configuration buttons and stuff:");
        title.setFont(componentsDescriptionLabelsFont);

        title.setHorizontalAlignment(SwingConstants.CENTER);


        add(title);
        add(startStopButton);

        add(new JLabel());

        JLabel executedCommandComposerLabel = new JLabel();
        executedCommandComposerLabel.setText("Ping that will be executed:");
        executedCommandComposerLabel.setFont(componentsDescriptionLabelsFont);
        add(executedCommandComposerLabel);

        // A wrapper panel to offset executedCommandComposerPanel
        // from being in the middle. Quite bad but can't think of better solution
        JPanel executedCommandComposerPanelWrapper = new JPanel();
        executedCommandComposerPanelWrapper.setLayout(new GridLayout(1,2));

        executedCommandComposerPanel = new ExecutedCommandComposerPanel(startStopButton);

        executedCommandComposerPanelWrapper.add(executedCommandComposerPanel);
        executedCommandComposerPanelWrapper.add(new JLabel());
        add(executedCommandComposerPanelWrapper);

        // Acts as a <br><br> sequence.
        // Will be replaced once this space is occupied with something useful
        add(new JLabel());
        add(new JLabel());

        strokeColorsLabel = new JLabel();
        strokeColorsLabel.setText("Select color for chart line:");
        strokeColorsLabel.setFont(componentsDescriptionLabelsFont);
        add(strokeColorsLabel);

        add(strokeColorsPanel);
    }

    public void setChartRenderer(XYLineAndShapeRenderer renderer) {
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
