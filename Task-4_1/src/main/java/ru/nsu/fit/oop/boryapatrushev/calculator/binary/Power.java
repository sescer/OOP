package ru.nsu.fit.oop.boryapatrushev.calculator.binary;
import ru.nsu.fit.oop.boryapatrushev.calculator.Binary;

public class Power extends Binary {

    public static final String symbol = "^";

    @Override
    public double execute() {
        return Math.pow(getArgument(0), getArgument(1));
    }
}