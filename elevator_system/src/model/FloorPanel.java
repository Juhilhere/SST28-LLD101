package model;

import enums.Direction;
import service.ElevatorController;

public class FloorPanel {

    private int floor;
    private ElevatorController controller;

    public FloorPanel(int floor, ElevatorController controller) {
        this.floor = floor;
        this.controller = controller;
    }

    public void pressUp() {
        controller.handleRequest(new Request(floor, Direction.UP));
    }

    public void pressDown() {
        controller.handleRequest(new Request(floor, Direction.DOWN));
    }
}
