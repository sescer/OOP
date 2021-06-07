package nsu.fit.oop.boryapatrushev.task_2_2_1;

import nsu.fit.oop.boryapatrushev.task_2_2_1.Manager.OrderManager;

import java.util.Scanner;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        OrderManager orderManager = new OrderManager();

        ConsoleApplication consoleApplication = new ConsoleApplication(args, orderManager);
        if (!consoleApplication.start()) {
            System.exit(0);
        }

        orderManager.init("bakers.json", "couriers.json", "config.json");

        while (true) {
            if (input.nextLine().equals("+")) {
                orderManager.addOrder();
            } else {
                orderManager.stop();
                break;
            }
        }
    }
}
