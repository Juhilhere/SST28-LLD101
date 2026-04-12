package model;

public class ElevatorPanel {

    private ElevatorCar elevator;

    public ElevatorPanel(ElevatorCar elevator) {
        this.elevator = elevator;
    }

    public void pressFloor(int floor) {
        elevator.addRequest(floor);
    }

    public void openDoor() {
        elevator.openDoor();
    }

    public void closeDoor() {
        elevator.closeDoor();
    }

    public void pressAlarm() {
        elevator.triggerAlarm();
    }
}
