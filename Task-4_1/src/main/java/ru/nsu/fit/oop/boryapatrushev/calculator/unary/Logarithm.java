package ru.nsu.fit.oop.boryapatrushev.calculator.unary;
import ru.nsu.fit.oop.boryapatrushev.calculator.Unary;

public class Logarithm extends Unary {

    public static final String symbol = "log";

    @Override
    public double execute() {
        return Math.log(getArgument(0));
    }
}
