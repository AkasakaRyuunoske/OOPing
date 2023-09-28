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
import java.util.Arrays;

public class LogsPanel extends JPanel {
    private JTextArea logsScreen;
    private JLabel title;
    private JScrollPane screenScroll;

    private int attemptsCounter = 0;

    private XYSeries series;

    private String[] commands = {"C:\\Windows\\System32\\ping.exe", "www.youtube.com", "-n", "200"};

    private String text;
    private String ms;
    private int endOfDocument;
    public LogsPanel(){
        setLayout(new GridLayout(2,1));
        logsScreen = new JTextArea();

        logsScreen.append("Here you will see logs of ping execution \n");

        screenScroll = new JScrollPane(logsScreen);
        screenScroll.createHorizontalScrollBar();
        screenScroll.createVerticalScrollBar();
        screenScroll.setAutoscrolls(false);

        title = new JLabel("Program logs:");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        add(title);
        add(screenScroll);

        redirectCLIOutput(logsScreen, commands);
    }

    private void redirectCLIOutput(JTextArea screen, String[] commands) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            System.out.println(process.info());
            InputStream inputStream = process.getInputStream();

            Thread outputReaderThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        appendText((attemptsCounter + 1) + ": " + line, screen);
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
            endOfDocument = screen.getDocument().getLength();

            try {
                ms = text.split("durata=", 2)[1].split("ms", 2)[0];
            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                String errorMessage = "Something went wrong. \n";

                screen.append(errorMessage);
                screen.setCaretPosition(endOfDocument);

                try {
                    System.out.println("Start offset: " + (endOfDocument - errorMessage.length()));
                    System.out.println("End offset: " + endOfDocument);

                    screen.getHighlighter().addHighlight((endOfDocument - errorMessage.length()), endOfDocument, new DefaultHighlighter.DefaultHighlightPainter(Color.red));
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }

                return;
            }


            attemptsCounter++;

            series.add(attemptsCounter, Integer.valueOf(ms));

            screen.append(text + "\n");
            screen.setCaretPosition(screen.getDocument().getLength());

            try {

                if (Integer.parseInt(ms) > 0 && Integer.parseInt(ms) <= 80) {
                    screen.getHighlighter().addHighlight(endOfDocument, endOfDocument + text.length(), new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN));

                    return;
                }

                if (Integer.parseInt(ms) >= 80 && Integer.parseInt(ms) <= 300) {
                    screen.getHighlighter().addHighlight(endOfDocument, endOfDocument + text.length(), new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));

                } else {

                    screen.getHighlighter().addHighlight(endOfDocument, endOfDocument + text.length(), new DefaultHighlighter.DefaultHighlightPainter(Color.RED));

                }



//                screen.append(text + "\n");
//                screen.setCaretPosition(screen.getDocument().getLength());

            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }

        });
    }

//    private void appendText(String text, JTextArea screen) {
//        SwingUtilities.invokeLater(() -> {
//            this.text = text;
//            endOfDocument = screen.getDocument().getLength();
//
//            try {
//                ms = text.split("time=", 2)[1].split("ms", 2)[0];
//            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
//                String errorMessage = "Something went wrong. \n";
//
//                screen.append(errorMessage);
//                screen.setCaretPosition(endOfDocument);
//
//                try {
//                    System.out.println("Start offset: " + (endOfDocument - errorMessage.length()));
//                    System.out.println("End offset: " + endOfDocument);
//
//                    screen.getHighlighter().addHighlight((endOfDocument - errorMessage.length()), endOfDocument, new DefaultHighlighter.DefaultHighlightPainter(Color.red));
//                } catch (BadLocationException e) {
//                    throw new RuntimeException(e);
//                }
//
//                return;
//            }
//
//
//            attemptsCounter++;
//
//            series.add(attemptsCounter, Integer.valueOf(ms));
//
//
//
//            try {
//                System.out.println("Start offset: " + (endOfDocument - text.length()));
//                System.out.println("End offset: " + endOfDocument);
//
//                if (Integer.parseInt(ms) > 0 && Integer.parseInt(ms) <= 80) {
//                    screen.getHighlighter().addHighlight((endOfDocument - text.length()), endOfDocument, new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN));
//
//                    screen.append(text + "\n");
//                    screen.setCaretPosition(screen.getDocument().getLength());
//
//                    return;
//                }
//
//                if (Integer.parseInt(ms) >= 80 && Integer.parseInt(ms) <= 300) {
//                    screen.getHighlighter().addHighlight((endOfDocument - text.length()), endOfDocument, new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));
//
//                } else {
//
//                    screen.getHighlighter().addHighlight((endOfDocument - text.length()), endOfDocument, new DefaultHighlighter.DefaultHighlightPainter(Color.RED));
//
//                }
//
//                screen.append(text + "\n");
//                screen.setCaretPosition(screen.getDocument().getLength());
//
//            } catch (BadLocationException e) {
//                throw new RuntimeException(e);
//            }
//
//        });
//    }

    public void setSeries(XYSeries series){
        this.series = series;
    }
}
