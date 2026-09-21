package OOPing;

import org.jfree.data.xy.XYSeries;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LogsPanel extends JPanel {
    private JPanel statisticsPanel;
    private JTextArea logsScreen;
    private JLabel title;

    private JLabel greensLabel;
    private JLabel greensCounterLabel;
    private int greens_counter = 0;

    private JLabel redsLabel;
    private JLabel redsCounterLabel;
    private int reds_counter = 0;

    private JLabel yellowsLabel;
    private JLabel yellowsCounterLabel;
    private int yellows_counter = 0;
    private JScrollPane screenScroll;
    private int attemptsCounter = 0;
    private XYSeries series;

    private String[] commands = {"C:\\Windows\\System32\\ping.exe", "www.youtube.com", "-n", "200000"};

    private String ms;
    private int endOfDocument;
    private int average = 0;
    private int displayedAverage = 0;
    private final JLabel averageLabel;

    private Process process;
    private Thread outputReaderThread;

    public LogsPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        statisticsPanel = new JPanel();
        statisticsPanel.setLayout(new GridLayout(2, 3));

        logsScreen = new JTextArea();

        logsScreen.append("Here you will see logs of OOPing execution \n");

        screenScroll = new JScrollPane(logsScreen);
        screenScroll.createHorizontalScrollBar();
        screenScroll.createVerticalScrollBar();
        screenScroll.setAutoscrolls(false);

        title = new JLabel("Program logs:");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalTextPosition(SwingConstants.TOP);
        title.setVerticalAlignment(SwingConstants.TOP);

        averageLabel = new JLabel();
        averageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        averageLabel.setVerticalTextPosition(SwingConstants.TOP);
        averageLabel.setVerticalAlignment(SwingConstants.TOP);
        averageLabel.setText("Average: " + displayedAverage + "ms");

        greensLabel = new JLabel();
        greensLabel.setHorizontalAlignment(SwingConstants.CENTER);
        greensLabel.setVerticalTextPosition(SwingConstants.TOP);
        greensLabel.setVerticalAlignment(SwingConstants.TOP);
        greensLabel.setText("<= 80ms");

        redsLabel = new JLabel();
        redsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        redsLabel.setVerticalTextPosition(SwingConstants.TOP);
        redsLabel.setVerticalAlignment(SwingConstants.TOP);
        redsLabel.setText(">= 300ms");

        yellowsLabel = new JLabel();
        yellowsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        yellowsLabel.setVerticalTextPosition(SwingConstants.TOP);
        yellowsLabel.setVerticalAlignment(SwingConstants.TOP);
        yellowsLabel.setText("< 300ms");

        statisticsPanel.add(greensLabel);
        statisticsPanel.add(redsLabel);
        statisticsPanel.add(yellowsLabel);

        // Statistics Panel stuff
        greensCounterLabel = new JLabel();
        greensCounterLabel.setBackground(Color.green);
        greensCounterLabel.setOpaque(true);

        redsCounterLabel = new JLabel();
        redsCounterLabel.setBackground(Color.red);
        redsCounterLabel.setOpaque(true);

        yellowsCounterLabel = new JLabel();
        yellowsCounterLabel.setBackground(Color.yellow);
        yellowsCounterLabel.setOpaque(true);

        statisticsPanel.add(greensCounterLabel);
        statisticsPanel.add(redsCounterLabel);
        statisticsPanel.add(yellowsCounterLabel);


        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;

        add(title, gridBagConstraints);


        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        add(screenScroll, gridBagConstraints);


        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        add(statisticsPanel, gridBagConstraints);


        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridx = 0;
        add(averageLabel, gridBagConstraints);

//        redirectCLIOutput();
    }

    public void startRedirectCLIOutput(String[] commands) {
        if (process != null && process.isAlive()) {
            return; // Already running
        }

        System.out.println("commands => " + commands);

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            processBuilder.redirectErrorStream(true);

            process = processBuilder.start();

            InputStream inputStream = process.getInputStream();

            outputReaderThread = new Thread(() -> {
                try (BufferedReader reader =
                             new BufferedReader(new InputStreamReader(inputStream))) {

                    String line;

                    while (!Thread.currentThread().isInterrupted()
                            && (line = reader.readLine()) != null) {

                        appendText(
                                (attemptsCounter + 1) + ": " + line,
                                logsScreen
                        );
                    }

                } catch (IOException e) {
                    // Expected when the process is stopped
                    if (!Thread.currentThread().isInterrupted()) {
                        e.printStackTrace();
                    }
                }
            });

            outputReaderThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRedirectCLIOutput() {
        if (process != null) {
            process.destroy();

            if (process.isAlive()) {
                process.destroyForcibly();
            }

            process = null;
        }

        if (outputReaderThread != null) {
            outputReaderThread.interrupt();
            outputReaderThread = null;
        }
    }

    private void appendText(String text, JTextArea screen) {
        SwingUtilities.invokeLater(() -> {
            endOfDocument = screen.getDocument().getLength();

            try {
                ms = text.split("time=", 2)[1].split("ms", 2)[0]; ///#todo make it language independent
            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                String errorMessage = "Something went wrong. \n";

                screen.append(errorMessage);
                screen.setCaretPosition(endOfDocument);

                try {
                    screen.getHighlighter().addHighlight((endOfDocument - errorMessage.length()), endOfDocument, new DefaultHighlighter.DefaultHighlightPainter(Color.red));

                } catch (BadLocationException badLocationException) {
                    JOptionPane.showConfirmDialog(null,
                            "Something went wrong. Error message: " + badLocationException.getMessage(),
                            "Critical Application Error",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.ERROR_MESSAGE);
                }

                return;
            }

            attemptsCounter++;

            countAveragePing(Integer.parseInt(ms));
            averageLabel.setText("Average: " + displayedAverage + "ms");
            series.add(attemptsCounter, Integer.valueOf(ms));

            screen.append(text + "\n");
            screen.setCaretPosition(screen.getDocument().getLength());

            try {
                if (Integer.parseInt(ms) > 0 && Integer.parseInt(ms) <= 80) {
                    screen.getHighlighter().addHighlight(endOfDocument, endOfDocument + text.length(), new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN));
                    greens_counter++;
                    greensCounterLabel.setText(String.valueOf(greens_counter));

                    return;
                }

                if (Integer.parseInt(ms) >= 80 && Integer.parseInt(ms) <= 300) {
                    screen.getHighlighter().addHighlight(endOfDocument, endOfDocument + text.length(), new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));
                    yellows_counter++;
                    yellowsCounterLabel.setText(String.valueOf(yellows_counter));

                } else {

                    screen.getHighlighter().addHighlight(endOfDocument, endOfDocument + text.length(), new DefaultHighlighter.DefaultHighlightPainter(Color.RED));
                    reds_counter++;
                    redsCounterLabel.setText(String.valueOf(reds_counter));

                }

            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }

        });
    }

    public void setSeries(XYSeries series) {
        this.series = series;
    }

    private void countAveragePing(int nextValue) {
        average += nextValue;
        displayedAverage = average / attemptsCounter;
    }
}
