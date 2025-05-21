import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class RaceTrack {
    private final int finishLine;
    private final ArrayList<Car> cars = new ArrayList<>();
    private int currentCarIndex = 0;
    private boolean raceFinished = false;
    private final Random random = new Random();
    private final JTextArea raceLog;

    public RaceTrack(int numCars, int finishLine, JTextArea raceLog) {
        this.finishLine = finishLine;
        this.raceLog = raceLog;


        for (int i = 0; i < numCars; i++) {
            cars.add(new Car(i, this));
        }
    }

    public boolean isRaceFinished() {
        return raceFinished;
    }

    public boolean isCarTurn(int carId) {
        return cars.get(currentCarIndex).getCarId() == carId;
    }

    public void nextCarTurn() {
        if (raceFinished) return;

        currentCarIndex = (currentCarIndex + 1) % cars.size();

        for (Car car : cars) {
            if (car.getPosition() >= finishLine) {
                raceFinished = true;
                logEvent("🚩 Car " + car.getCarId() + " wins the race!");
                break;
            }
        }
    }

    public void checkObstaclesAndBoosts(Car car) {
        int chance = random.nextInt(100);

        if (chance < 10) {
            logEvent("✨ Car " + car.getCarId() + " got a speed boost!");
            car.boost();
        } else if (chance < 20) {
            logEvent("🚧 Car " + car.getCarId() + " hit an obstacle!");
            car.adjustSpeed(-1);
        }
    }

    public void startRace() {
        logEvent("🏁 Race started!");
        for (Car car : cars) {
            car.start();
        }
    }

    private void logEvent(String event) {
        raceLog.append(event + "\n");
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public int getFinishLine() {
        return finishLine;
    }
}
