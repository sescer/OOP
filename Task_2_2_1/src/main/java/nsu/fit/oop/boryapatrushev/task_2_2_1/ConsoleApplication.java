package nsu.fit.oop.boryapatrushev.task_2_2_1;

import nsu.fit.oop.boryapatrushev.task_2_2_1.Manager.OrderManager;

/**
 * Console application for simulating pizzeria
 * USAGE: application_name [OPTION]
 * Possible options:
 *      -h show help and short info
 *      -l activate additional logs (use for debug purpose)
 *      -f write final statistics into file (default: stdout)
 * Use [+] to add order and [stop] to finish session.
 */

public class ConsoleApplication {

    private final String[] args;
    private final OrderManager orderManager;

    public ConsoleApplication(String[] args,
                              OrderManager orderManager) {
        this.args = args;
        this.orderManager = orderManager;
    }

    /**
     * Method for parsing input arguments
     * @return true if start is possible
     */
    public boolean start() {

        for (String argument: args) {
            switch (argument) {
                case "-l" -> orderManager.setAdditionalLogs(true);
                case "-f" -> orderManager.setPath(true);
                default -> {
                    help();
                    return false;
                }
            }
        }

        return true;
    }

    private void help() {
        System.out.println("""
                Usage: programName [OPTION]...
                Pizzeria simulation
                \t-h\tshow help and short info
                \t-l\tactivate additional logs (use for debug purpose)
                \t-f\twrite final statistics into file (default: stdout)""");
        System.out.println("Use [+] to add order and [any key] to finish session");
    }
}
