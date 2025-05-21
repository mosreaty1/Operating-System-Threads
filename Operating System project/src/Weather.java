import javax.swing.*;
import java.util.Random;

public class Weather extends Thread {
    private final RaceTrack raceTrack;
    private final JTextArea raceLog;
    private String currentWeather = "Clear";

    public Weather(RaceTrack raceTrack, JTextArea raceLog) {
        this.raceTrack = raceTrack;
        this.raceLog = raceLog;
    }

    @Override
    public void run() {
        String[] weatherConditions = {"Clear", "Rain", "Fog"};
        Random random = new Random();

        while (!raceTrack.isRaceFinished()) {
            currentWeather = weatherConditions[random.nextInt(weatherConditions.length)];
            raceLog.append("Weather update: " + currentWeather + "\n");

            for (Car car : raceTrack.getCars()) {
                if (currentWeather.equals("Rain")) {
                    car.adjustSpeed(-1);
                } else if (currentWeather.equals("Clear")) {
                    car.adjustSpeed(1);
                }
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
