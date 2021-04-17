package ru.nsu.fit.oop.boryapatrushev.calculator.unary;
import ru.nsu.fit.oop.boryapatrushev.calculator.Unary;

public class Sine extends Unary {

    public static final String symbol = "sin";

    @Override
    public double execute() {
        return Math.sin(getArgument(0));
    }
}
