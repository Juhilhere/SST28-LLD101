package strategy;

import model.*;
import enums.*;
import java.util.*;

public class NearestElevatorStrategy implements SchedulerStrategy {

    public ElevatorCar selectElevator(List<ElevatorCar> elevators, Request request) {

        ElevatorCar best = null;
        int minDist = Integer.MAX_VALUE;

        for (ElevatorCar e : elevators) {

            if (e.getState() == ElevatorState.MAINTENANCE) continue;

            int dist = Math.abs(e.getCurrentFloor() - request.getFloor());

            if (dist < minDist) {
                minDist = dist;
                best = e;
            }
        }

        return best;
    }
}
