import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RaceGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Round-Robin Racing Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);


            JTextArea raceLog = new JTextArea(10, 30);
            raceLog.setEditable(false);
            raceLog.setFont(new Font("Monospaced", Font.PLAIN, 14));
            JScrollPane scrollPane = new JScrollPane(raceLog);
            scrollPane.setBorder(BorderFactory.createTitledBorder("Race Log"));


            RaceTrack raceTrack = new RaceTrack(5, 100, raceLog);
            RacePanel racePanel = new RacePanel(raceTrack);
            racePanel.setBorder(BorderFactory.createTitledBorder("Race Track"));


            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());


            JButton startButton = new JButton("Start Race");
            startButton.setFont(new Font("Arial", Font.BOLD, 16));
            startButton.setBackground(new Color(34, 139, 34));
            startButton.setForeground(Color.WHITE);
            startButton.setFocusPainted(false);
            startButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            startButton.setPreferredSize(new Dimension(150, 40));
            startButton.addActionListener(e -> {
                raceTrack.startRace();
                startButton.setEnabled(false);
            });


            buttonPanel.add(startButton);
            buttonPanel.setBackground(Color.LIGHT_GRAY);


            racePanel.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.LIGHT_GRAY);


            JLabel titleLabel = new JLabel("Welcome to the Round-Robin Racing Game!", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setForeground(new Color(70, 130, 180));
            titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(Color.LIGHT_GRAY);
            mainPanel.add(titleLabel, BorderLayout.NORTH);
            mainPanel.add(racePanel, BorderLayout.CENTER);
            mainPanel.add(scrollPane, BorderLayout.EAST);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);


            frame.add(mainPanel);
            frame.setVisible(true);


            Timer timer = new Timer(100, e -> racePanel.repaint());
            timer.start();


            Weather weather = new Weather(raceTrack, raceLog);
            weather.start();
        });
    }
}