import model.*;
import service.*;
import strategy.*;

public class Main {

    public static void main(String[] args) {

        ElevatorController controller =
                new ElevatorController(new NearestElevatorStrategy());

        ElevatorCar e1 = new ElevatorCar("E1", 750);
        ElevatorCar e2 = new ElevatorCar("E2", 750);

        controller.addElevator(e1);
        controller.addElevator(e2);

        FloorPanel floor5 = new FloorPanel(5, controller);
        floor5.pressUp();

        ElevatorPanel inside = new ElevatorPanel(e1);

        inside.pressFloor(8);

        e1.addLoad(800);
        inside.closeDoor();

        for (int i = 0; i < 10; i++) {
            controller.step();
        }
    }
}
