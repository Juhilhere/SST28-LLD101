package model;

import enums.*;
import java.util.*;

public class ElevatorCar {

    private String id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;

    private int capacity;
    private int currentLoad;

    private boolean doorOpen;

    private TreeSet<Integer> requests;

    public ElevatorCar(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.doorOpen = false;
        this.requests = new TreeSet<>();
    }

    public String getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public void addRequest(int floor) {
        requests.add(floor);
    }

    public void addLoad(int weight) {
        currentLoad += weight;
    }

    public void removeLoad(int weight) {
        currentLoad -= weight;
    }

    public void openDoor() {
        doorOpen = true;
        System.out.println(id + " door opened at floor " + currentFloor);
    }

    public void closeDoor() {
        if (currentLoad > capacity) {
            triggerAlarm();
            System.out.println(id + " overloaded, cannot close door");
            return;
        }
        doorOpen = false;
        System.out.println(id + " door closed");
    }

    public void triggerAlarm() {
        System.out.println("ALARM triggered in " + id);
    }

    public void move() {

        if (state == ElevatorState.MAINTENANCE) return;
        if (doorOpen) return;

        if (requests.isEmpty()) {
            direction = Direction.IDLE;
            state = ElevatorState.IDLE;
            return;
        }

        state = ElevatorState.MOVING;

        int target = requests.first();

        if (target > currentFloor) {
            direction = Direction.UP;
            currentFloor++;
        } else if (target < currentFloor) {
            direction = Direction.DOWN;
            currentFloor--;
        }

        if (currentFloor == target) {
            openDoor();
            requests.remove(target);
        }
    }
}
