package ru.nsu.fit.oop.boryapatrushev.calculator.unary;
import ru.nsu.fit.oop.boryapatrushev.calculator.Unary;

public class Cosine extends Unary {

    public static final String symbol = "cos";

    @Override
    public double execute() {
        return Math.cos(getArgument(0));
    }
}