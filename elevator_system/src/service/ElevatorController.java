package service;

import model.*;
import strategy.*;
import java.util.*;

public class ElevatorController {

    private List<ElevatorCar> elevators;
    private SchedulerStrategy strategy;

    public ElevatorController(SchedulerStrategy strategy) {
        this.strategy = strategy;
        this.elevators = new ArrayList<>();
    }

    public void addElevator(ElevatorCar e) {
        elevators.add(e);
    }

    public void handleRequest(Request request) {

        ElevatorCar selected = strategy.selectElevator(elevators, request);

        if (selected == null) {
            System.out.println("No elevator available");
            return;
        }

        selected.addRequest(request.getFloor());

        System.out.println("Assigned " + selected.getId() +
                " to floor " + request.getFloor());
    }

    public void step() {
        for (ElevatorCar e : elevators) {
            e.move();
        }
    }
}
