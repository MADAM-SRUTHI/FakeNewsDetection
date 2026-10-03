import javax.swing.*;
import java.awt.*;

public class FakeNewsGUI {

    static int totalChecks = 0;
    static int fakeCount = 0;
    static int suspiciousCount = 0;
    static int realCount = 0;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Fake News Detection System");

        frame.setSize(900, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // TITLE
        JLabel title = new JLabel(
                "FAKE NEWS DETECTION SYSTEM",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 26));
        frame.add(title, BorderLayout.NORTH);

        // MAIN PANEL
        JPanel centerPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        // NEWS INPUT
        JTextArea newsArea = new JTextArea();

        newsArea.setFont(
                new Font("Arial", Font.PLAIN, 17)
        );

        newsArea.setLineWrap(true);
        newsArea.setWrapStyleWord(true);

        JScrollPane newsScroll =
                new JScrollPane(newsArea);

        centerPanel.add(
                newsScroll,
                BorderLayout.CENTER
        );

        // HISTORY
        JTextArea historyArea = new JTextArea();

        historyArea.setEditable(false);
        historyArea.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        JScrollPane historyScroll =
                new JScrollPane(historyArea);

        historyScroll.setPreferredSize(
                new Dimension(320, 0)
        );

        centerPanel.add(
                historyScroll,
                BorderLayout.EAST
        );

        frame.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // BUTTONS
        JPanel buttonPanel = new JPanel();

        JButton checkButton =
                new JButton("CHECK NEWS");

        JButton clearButton =
                new JButton("CLEAR");

        JButton aboutButton =
                new JButton("ABOUT");

        JButton logoutButton =
                new JButton("LOGOUT");

        buttonPanel.add(checkButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(aboutButton);
        buttonPanel.add(logoutButton);

        // STATISTICS
        JPanel statsPanel =
                new JPanel(
                        new GridLayout(1, 4, 10, 10)
                );

        JLabel totalLabel =
                new JLabel(
                        "Total Checks: 0",
                        SwingConstants.CENTER
                );

        JLabel fakeLabel =
                new JLabel(
                        "Fake: 0",
                        SwingConstants.CENTER
                );

        JLabel suspiciousLabel =
                new JLabel(
                        "Suspicious: 0",
                        SwingConstants.CENTER
                );

        JLabel realLabel =
                new JLabel(
                        "Real: 0",
                        SwingConstants.CENTER
                );

        statsPanel.add(totalLabel);
        statsPanel.add(fakeLabel);
        statsPanel.add(suspiciousLabel);
        statsPanel.add(realLabel);

        // BOTTOM PANEL
        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );

        bottomPanel.add(
                buttonPanel,
                BorderLayout.NORTH
        );

        bottomPanel.add(
                statsPanel,
                BorderLayout.SOUTH
        );

        frame.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // CHECK NEWS
        checkButton.addActionListener(e -> {

            String news =
                    newsArea.getText().trim();

            if (news.isEmpty()) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please enter some news first!"
                );

                return;
            }

            String text =
                    news.toLowerCase();

            int suspiciousWords = 0;

            if (text.contains("click here"))
                suspiciousWords++;

            if (text.contains("free money"))
                suspiciousWords++;

            if (text.contains("you won"))
                suspiciousWords++;

            if (text.contains("lottery"))
                suspiciousWords++;

            if (text.contains("urgent"))
                suspiciousWords++;

            String result;

            if (suspiciousWords >= 2) {

                result = "FAKE NEWS";
                fakeCount++;

            } else if (suspiciousWords == 1) {

                result = "SUSPICIOUS NEWS";
                suspiciousCount++;

            } else {

                result = "POSSIBLY REAL NEWS";
                realCount++;
            }

            totalChecks++;

            // UPDATE STATISTICS
            totalLabel.setText(
                    "Total Checks: " + totalChecks
            );

            fakeLabel.setText(
                    "Fake: " + fakeCount
            );

            suspiciousLabel.setText(
                    "Suspicious: " + suspiciousCount
            );

            realLabel.setText(
                    "Real: " + realCount
            );

            // RESULT
            JOptionPane.showMessageDialog(
                    frame,
                    "Prediction: " + result,
                    "Detection Result",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // HISTORY
            historyArea.append(
                    "News: " + news + "\n"
                    + "Result: " + result + "\n"
                    + "-----------------------------\n"
            );
        });

        // CLEAR
        clearButton.addActionListener(e -> {

            newsArea.setText("");

        });

        // ABOUT
        aboutButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    frame,
                    "Fake News Detection System\n\n"
                    + "Developed using Java Swing\n"
                    + "Purpose: Detect suspicious news content\n"
                    + "Project Type: CSE Mini Project",
                    "About Project",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        // LOGOUT
        logoutButton.addActionListener(e -> {

            frame.dispose();

            Login.main(new String[]{});

        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}