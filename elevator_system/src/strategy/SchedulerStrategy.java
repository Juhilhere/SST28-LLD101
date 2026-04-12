package strategy;

import model.*;
import java.util.*;

public interface SchedulerStrategy {
    ElevatorCar selectElevator(List<ElevatorCar> elevators, Request request);
}
