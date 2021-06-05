package nsu.fit.oop.boryapatrushev.task_2_2_1.Order;

import nsu.fit.oop.boryapatrushev.task_2_2_1.Storage.IncomingOrders;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Storage.Warehouse;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Workers.Worker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class for displaying current information about the service
 */

public class Logger {

    private final Warehouse warehouse;
    private final IncomingOrders incomingOrders;
    private final boolean additionalInfo;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public Logger(Warehouse warehouse,
                  IncomingOrders incomingOrders,
                  boolean additionalInfo) {
        this.incomingOrders = incomingOrders;
        this.warehouse = warehouse;
        this.additionalInfo = additionalInfo;
    }

    /**
     * Method for outputting information to the stdout
     * Message based on the current status of the order
     * @param worker The worker calling this method
     * @param order Order information about which worker want to display
     */
    public synchronized void log(Worker worker, Order order) {

        LocalDateTime now = LocalDateTime.now();
        String date = "[" + dtf.format(now) + "] ";

        switch (order.getStatus()) {
            case inQueue:
                if (additionalInfo) {
                    System.out.println(date + order + " accepted "
                            + "[current size of incoming queue: " + incomingOrders.getCurrentSize() + "]");
                } else {
                    System.out.println(date + order + " accepted");
                }
                break;

            case gettingReady:
                if (additionalInfo) {
                    System.out.println(date + order + " was taken by baker " + worker
                            + " [current size of incoming queue: " + incomingOrders.getCurrentSize() + "]");
                } else {
                    System.out.println(date + order + " was taken by baker " + worker);
                }
                break;

            case ready:
                System.out.println(date + order + " is ready by baker " + worker);
                break;

            case movedToWarehouse:
                if (additionalInfo) {
                    System.out.println(date + order + " moved to warehouse"
                            + " [current size of warehouse: " + warehouse.getSize()
                            + " of " + warehouse.getCapacity() + "]");
                } else {
                    System.out.println(date + order + " moved to warehouse");
                }
                break;

            case isTaken:
                if (additionalInfo) {
                    System.out.println(date + order + " was taken from warehouse by courier " + worker
                            + " [current size of warehouse: " + warehouse.getSize()
                            + " of " + warehouse.getCapacity() + "] " + worker.info());
                } else {
                    System.out.println(date + order + " was taken from warehouse by courier " + worker);
                }
                break;

            case inDelivery:
                System.out.println(date + order + " in delivery by courier " + worker);
                break;
            case delivered:
                System.out.println(date + order + " is delivered by courier " + worker);
                break;
            default:
                break;
        }
        order.changeStatus();
    }

    /**
     * Method that displays the summary report about the session
     * @param journal array with all orders
     * @param path file path (if null - prints to stdout)
     * @throws IOException if can't create file
     */
    public synchronized void logStats(ArrayList<Order> journal,
                                      String path) throws IOException {

        if (path == null) {
            System.out.println("=== YOUR DAILY BUSINESS REPORT ===");
            for (Order record : journal) {
                System.out.println(record + " was baked by \"" + record.getBakerName()
                        + "\" and delivered by \"" + record.getCourierName()
                        + "\" " + "[" + dtf.format(record.acceptedTime) + " - "
                        + dtf.format(record.deliveredTime) + "]");
            }
        } else {
            PrintWriter writer = new PrintWriter(new FileWriter(new File(path), false));
            writer.println("=== YOUR DAILY BUSINESS REPORT ===");
            for (Order record : journal) {
                writer.print(record + " was baked by \"" + record.getBakerName()
                        + "\" and delivered by \"" + record.getCourierName()
                        + "\" " + "[" + dtf.format(record.acceptedTime) + " - "
                        + dtf.format(record.deliveredTime) + "]\n");
            }
            writer.close();
        }
    }

    /**
     * Additional logging method for exiting workers
     * @param worker worker calling this method
     */
    public synchronized void exitLog(Worker worker) {
        if (additionalInfo) {
            System.out.println("Worker " + worker + " finished for today");
        }
    }

    /**
     * Logging method for custom messaging
     * @param message message to log
     */
    public synchronized void customLog(String message) {
        System.out.println(message);
    }
}
