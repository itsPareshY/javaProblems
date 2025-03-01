package com.paresh.practice.design.patterns.problem;

import java.util.*;

/**
 * State Pattern - Defines the interface for lift states
 */
interface LiftState {
    void move(Lift lift, int floor);
}

/**
 * Concrete States - Moving, Idle, and Maintenance states for the lift
 */
class MovingState implements LiftState {
    public void move(Lift lift, int floor) {
        System.out.println("[DEBUG] Lift moving to floor: " + floor);
        lift.setCurrentFloor(floor);
        lift.setState(new IdleState()); // After moving, the lift becomes idle
    }
}

class IdleState implements LiftState {
    public void move(Lift lift, int floor) {
        System.out.println("[DEBUG] Lift is idle, moving to floor: " + floor);
        lift.setState(new MovingState()); // Transition to moving state
        lift.move(floor);
    }
}

class MaintenanceState implements LiftState {
    public void move(Lift lift, int floor) {
        System.out.println("[DEBUG] Lift is under maintenance. Cannot move.");
    }
}

/**
 * Observer Pattern - Users (observers) receive notifications when the lift reaches a floor
 */
interface Observer {
    void update(int floor);
}

class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void update(int floor) {
        System.out.println("[DEBUG] " + name + " notified: Lift reached floor " + floor);
    }
}

/**
 * Strategy Pattern - Determines which lift to assign based on its current position and state
 */
interface LiftAssignmentStrategy {
    Lift assignLift(List<Lift> lifts, int requestedFloor, String direction);
}

class NearestLiftStrategy implements LiftAssignmentStrategy {
    public Lift assignLift(List<Lift> lifts, int requestedFloor, String direction) {
        System.out.println("[DEBUG] Assigning lift for floor: " + requestedFloor + " with direction: " + direction);
        Lift bestLift = null;
        int minDistance = Integer.MAX_VALUE;

        for (Lift lift : lifts) {
            if (lift.getState() instanceof MaintenanceState) continue; // Skip lifts under maintenance
            int distance = Math.abs(lift.getCurrentFloor() - requestedFloor);
            if (distance < minDistance) {
                minDistance = distance;
                bestLift = lift;
            }
        }
        System.out.println("[DEBUG] Assigned lift at floor: " + (bestLift != null ? bestLift.getCurrentFloor() : "None"));
        return bestLift;
    }
}

/**
 * Command Pattern - Represents lift movement operations as commands
 */
interface Command {
    void execute();
}

class MoveCommand implements Command {
    private Lift lift;
    private int floor;

    public MoveCommand(Lift lift, int floor) {
        this.lift = lift;
        this.floor = floor;
    }

    public void execute() {
        System.out.println("[DEBUG] Executing move command to floor: " + floor);
        lift.move(floor);
    }
}

class LiftInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        System.out.println("[DEBUG] Invoking command execution");
        command.execute();
    }
}

/**
 * Lift class - Implements state, observer, and command patterns
 */
class Lift {
    private int currentFloor = 0;
    private String direction = "IDLE";
    private LiftState state = new IdleState();
    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(currentFloor);
        }
    }

    public void move(int floor) {
        System.out.println("[DEBUG] Request to move lift from floor " + currentFloor + " to " + floor);
        // Determine direction of movement
        if (floor > currentFloor) {
            direction = "UP";
        } else if (floor < currentFloor) {
            direction = "DOWN";
        } else {
            direction = "IDLE";
        }
        System.out.println("[DEBUG] Lift direction: " + direction);

        // Execute state-specific movement behavior
        state.move(this, floor);

        // Notify all users when the lift reaches the floor
        notifyObservers();
    }

    public void setState(LiftState state) {
        this.state = state;
    }

    public LiftState getState() {
        return state;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int floor) {
        this.currentFloor = floor;
    }
}

/**
 * Singleton Pattern - LiftController manages multiple lifts
 */
class LiftController {
    private static volatile LiftController instance;
    private List<Lift> lifts = new ArrayList<>();
    private LiftAssignmentStrategy assignmentStrategy = new NearestLiftStrategy();

    private LiftController(int liftCount) {
        if (instance != null) {
            throw new RuntimeException("Controller already exists. Use getInstance() method.");
        }
        for (int i = 0; i < liftCount; i++) {
            lifts.add(new Lift());
        }
    }

    public static LiftController getInstance(int liftCount) {
        if (instance == null){
            synchronized (LiftController.class) {
                if (instance == null) {
                    instance = new LiftController(liftCount);
                }
            }
        }
        return instance;
    }

    public Lift requestLift(int floor, String direction) {
        System.out.println("[DEBUG] Lift requested at floor: " + floor + " with direction: " + direction);
        return assignmentStrategy.assignLift(lifts, floor, direction);
    }
}

/**
 * Main execution class
 */
public class LiftSystem {
    public static void main(String[] args) {
        // Create a lift controller with 3 lifts
        LiftController liftController = LiftController.getInstance(3);
        LiftInvoker invoker = new LiftInvoker();

        // Create users who will be notified when the lift reaches their floor
        User user1 = new User("Alice");
        User user2 = new User("Bob");

        // Request a lift to floor 5
        Lift assignedLift = liftController.requestLift(5, "UP");
        if (assignedLift != null) {
            assignedLift.attach(user1);
            assignedLift.attach(user2);

            // Create and execute a command to move the lift
            Command moveTo5 = new MoveCommand(assignedLift, 5);
            invoker.setCommand(moveTo5);
            invoker.executeCommand();
        } else {
            System.out.println("[DEBUG] No available lift to move to floor 5.");
        }
    }
}
