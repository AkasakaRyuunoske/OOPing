package OOPing;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class OOPingApp extends JFrame {

    String text;
    XYSeries series = new XYSeries("Ping Attempt");

    XYSeriesCollection dataset = new XYSeriesCollection();
    int i = 0;
    String ms;

    public OOPingApp() {
        setLayout(new GridLayout(1,3));
        JTextArea jTextArea = new JTextArea();

        JScrollPane scrollPane_kafka = new JScrollPane(jTextArea);
        scrollPane_kafka.createHorizontalScrollBar();
        scrollPane_kafka.createVerticalScrollBar();
        scrollPane_kafka.setBounds(300,300,600,200);

        String[] commands = {"C:\\Windows\\System32\\ping.exe", "www.youtube.com", "-n", "200"};

        JFreeChart chart = createChart();

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);

        JPanel panel = new JPanel();
        JLabel jLabel = new JLabel();

        jLabel.setText("Here will be configuration buttons and stuff");
        panel.add(jLabel);

        add(panel);
        add(chartPanel);
        add(scrollPane_kafka);

        redirectCLIOutput(jTextArea, commands);

        setSize(1280,720);
        setTitle("OOPing - OOPong");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JFreeChart createChart() {

        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Ping by Mokson",
                "Attempts",
                "ms",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                true
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.magenta);
        renderer.setSeriesStroke(0, new BasicStroke(3.5f));
        renderer.setDrawOutlines(true);

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.black);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.black);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Ping - Pong", new Font("Serif", java.awt.Font.BOLD, 18)));

        return chart;
    }


    private void redirectCLIOutput(JTextArea screen, String[] commands) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            InputStream inputStream = process.getInputStream();

            Thread outputReaderThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        appendText(line, screen);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            outputReaderThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendText(String text, JTextArea screen) {
        SwingUtilities.invokeLater(() -> {
            this.text = text;

            ms = text.split("time=", 2)[1].split("ms", 2)[0];

            i++;

            series.add(i, Integer.valueOf(ms));

            screen.append(text + "\n");
            screen.setCaretPosition(screen.getDocument().getLength());
        });
    }
}
