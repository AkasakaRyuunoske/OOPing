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

public class OOPingApp extends JFrame {
    XYSeries series = new XYSeries("Ping Attempt");
    XYSeriesCollection dataset = new XYSeriesCollection();

    LogsPanel logsPanel;
    ButtonsPanel buttonsPanel;

    public OOPingApp() {
        setLayout(new GridLayout(1, 3));

        JFreeChart chart = createChart();

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);



        logsPanel = new LogsPanel();
        logsPanel.setSeries(series);

        buttonsPanel = new ButtonsPanel();

        add(buttonsPanel);
        add(chartPanel);
        add(logsPanel);

        setSize(1280, 720);
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
}
