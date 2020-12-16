package ru.nsu.fit.oop.boryapatrushev.calculator;
import java.util.Scanner;


public class Calculator {
    public static void main(String[] args) {

        Parser calculator = new Parser();

        Scanner input = new Scanner(System.in);
        String expression;
        System.out.println("Enter the expression, please");
        System.out.println("Enter 'quit' to exit from calculator");

        while (input.hasNextLine()) {
            try {
                expression = input.nextLine();
                if(expression.equals("quit"))
                    break;
                System.out.println(">> " + calculator.calculate(expression));
            } catch (Exception e) {
                System.out.println(">> Error occurred (" + e.getMessage() + ")");
            }
        }
        input.close();
    }
}
