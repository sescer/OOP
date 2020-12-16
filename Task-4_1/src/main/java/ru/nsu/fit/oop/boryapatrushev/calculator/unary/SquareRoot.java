package ru.nsu.fit.oop.boryapatrushev.calculator.unary;
import ru.nsu.fit.oop.boryapatrushev.calculator.Unary;

public class SquareRoot extends Unary {

    public static final String symbol = "sqrt";

    @Override
    public double execute() {
        return Math.sqrt(getArgument(0));
    }
}
