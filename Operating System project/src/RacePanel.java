import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RacePanel extends JPanel {
    private final RaceTrack raceTrack;

    public RacePanel(RaceTrack raceTrack) {
        this.raceTrack = raceTrack;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<Car> cars = raceTrack.getCars();
        int finishLine = raceTrack.getFinishLine();


        g.setColor(Color.RED);
        g.drawLine(finishLine * 5, 0, finishLine * 5, getHeight());


        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            g.setColor(Color.BLUE);
            g.fillRect(car.getPosition() * 5, i * 50 + 20, 40, 20);
            g.setColor(Color.BLACK);
            g.drawString("Car " + car.getId(), car.getPosition() * 5, i * 50 + 15);
        }
    }
}
