package ru.nsu.fit.oop.boryapatrushev.calculator.unary;
import ru.nsu.fit.oop.boryapatrushev.calculator.Unary;

public class UnaryMinus extends Unary {

    public static final String symbol = "~";

    @Override
    public double execute() {
        return -getArgument(0);
    }

}
