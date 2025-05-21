public class Car extends Thread {
    private final int carId;
    private int position;
    private int speed;
    private boolean boosted = false;
    private final RaceTrack raceTrack;

    public Car(int carId, RaceTrack raceTrack) {
        this.carId = carId;
        this.position = 0;
        this.speed = 1 + (int) (Math.random() * 5);
        this.raceTrack = raceTrack;
    }

    public int getCarId() {
        return carId;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public void run() {
        while (!raceTrack.isRaceFinished()) {
            synchronized (raceTrack) {
                while (!raceTrack.isCarTurn(carId)) {
                    try {
                        raceTrack.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                if (boosted) {
                    position += speed * 2;
                    boosted = false;
                } else {
                    position += speed;
                }


                raceTrack.checkObstaclesAndBoosts(this);


                raceTrack.nextCarTurn();
                raceTrack.notifyAll();
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void boost() {
        boosted = true;
    }

    public void adjustSpeed(int adjustment) {
        speed = Math.max(1, speed + adjustment);
    }
}
