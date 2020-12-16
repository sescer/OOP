package ru.nsu.fit.oop.boryapatrushev.calculator.binary;

import ru.nsu.fit.oop.boryapatrushev.calculator.Binary;

public class Plus extends Binary {

    public static final String symbol = "+";

    @Override
    public double execute() {
        return getArgument(0) + getArgument(1);
    }
}