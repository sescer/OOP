package ru.nsu.fit.oop.boryapatrushev.calculator;

/**
 * Class for operations
 * Contains arity - how many arguments are needed for function
 * Arguments - list of arguments
 * Cnt - current number of arguments ready to be processed
 */

public abstract class Operation {

    public final int arity;
    private int cnt;
    private double[] arguments;

    Operation(int arity) {
        this.cnt = 0;
        this.arity = arity;
        this.arguments = new double[arity];
    }

    /**
     * Store input argument for future processing
     * @param argument numeric argument
     */
    public void setArgument(double argument) {
        arguments[cnt++] = argument;
    }

    /**
     * Get arguments for processing
     * @param id id of argument
     * @return argument to process
     */
    public double getArgument(int id) {
        return arguments[id];
    }

    /**
     * Check if current number of arguments satisfies arity of function
     * @return true - if satisfied, otherwise- false
     */
    public boolean readyToCalculate() {
        return arity == cnt;
    }

    /**
     * Set current number of arguments to 0
     * The main goal is to prepare for next calculations
     */
    public void reset() {
        this.cnt = 0;
    }

    /**
     * Abstract method which is implemented in child classes
     * @return double - result of execution
     */
    public abstract double execute();

}