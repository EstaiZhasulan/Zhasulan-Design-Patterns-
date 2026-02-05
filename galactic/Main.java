package edu.narxoz.galactic;

import edu.narxoz.galactic.bodies.Planet;
import edu.narxoz.galactic.bodies.SpaceStation;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.dispatcher.Dispatcher;
import edu.narxoz.galactic.dispatcher.Result;
import edu.narxoz.galactic.drones.HeavyDrone;
import edu.narxoz.galactic.drones.LightDrone;
import edu.narxoz.galactic.task.DeliveryTask;

public class Main {
    public static void main(String[] args) {
        Planet earth = new Planet("Earth", 0, 0, "Nitrogen-Oxygen");
        SpaceStation station = new SpaceStation("ISS", 100, 100, 5); // Дистанция ~141.4

        Cargo heavyCargo = new Cargo(40.0, "Mining Equipment");

        LightDrone lightDrone = new LightDrone("L-01", 20.0); // Макс 20кг
        HeavyDrone heavyDrone = new HeavyDrone("H-01", 100.0); // Макс 100кг

        DeliveryTask task = new DeliveryTask(earth, station, heavyCargo);
        Dispatcher dispatcher = new Dispatcher();

        System.out.println("--- Attempt 1: LightDrone (Too weak) ---");
        Result r1 = dispatcher.assignTask(task, lightDrone);
        System.out.println("Result: " + r1.ok() + ", Reason: " + r1.reason());

        System.out.println("\n--- Attempt 2: HeavyDrone (Suitable) ---");
        Result r2 = dispatcher.assignTask(task, heavyDrone);
        System.out.println("Result: " + r2.ok());

        if (r2.ok()) {
            System.out.println("Task Status: " + task.getState());
            System.out.println("Drone Status: " + heavyDrone.getStatus());

            double time = task.estimateTime();
            System.out.printf("Estimated Time: %.2f mins%n", time);

            System.out.println("\n--- Completing Task ---");
            Result r3 = dispatcher.completeTask(task);
            System.out.println("Completion Result: " + r3.ok());
            System.out.println("Final Task State: " + task.getState());
            System.out.println("Final Drone Status: " + heavyDrone.getStatus());
        }
    }
}