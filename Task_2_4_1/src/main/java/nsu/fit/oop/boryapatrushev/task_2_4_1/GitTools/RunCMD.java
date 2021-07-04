package nsu.fit.oop.boryapatrushev.task_2_4_1.GitTools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Wrapper class of {@link ProcessBuilder} class
 * Used for easy interaction with processBuilder
 * This class is necessary for console git interaction
 */
public class RunCMD {

    private final ProcessBuilder processBuilder;
    private Process process;

    /**
     * Constructor of the class
     */
    public RunCMD() {
        processBuilder = new ProcessBuilder();
    }

    /**
     * Set command line parameter
     * @param command command to set
     */
    public void command(String command) {
            processBuilder.command("cmd.exe", "/c", command);
    }

    /**
     * Starts the execution of specified command
     * @throws IOException if an I/O error occurs
     */
    public void start() throws IOException {
        process = processBuilder.start();
    }

    /**
     * Get inputStream of the process
     * @return mapped to input stream
     */
    public BufferedReader inputReader() {
        return new BufferedReader(new InputStreamReader(process.getInputStream()));
    }

    /**
     * Wait for process execution
     * @return exit code of executed process
     * @throws InterruptedException if the current thread is interrupted by another thread while it is waiting
     */
    public int waitForExec() throws InterruptedException {
        return process.waitFor();
    }

    /**
     * Set root directory of process
     * @param directory path to set
     */
    public void directory(String directory) {
        processBuilder.directory(new File(directory));
    }
}
