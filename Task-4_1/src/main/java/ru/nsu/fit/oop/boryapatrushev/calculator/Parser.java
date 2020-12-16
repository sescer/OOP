package ru.nsu.fit.oop.boryapatrushev.calculator;



/**
 * Class for parsing and processing expressions
 *
 */
public class Parser {

    private final Stack<Double> stack = new Stack<>(1000);

    /**
     * Public wrapper method for "process" function
     * The main class function needed to calculate the expression expr
     * @param expr Expression to evaluate
     * @return result of expression
     * @throws Exception according to process function
     */
    public double calculate(String expr) throws Exception {

        stack.clear();
        String[] tokens = tokenize(expr);
        int length = tokens.length;

        for(int i = length - 1; i >= 0; i--) {
            process(tokens[i]);
        }

        if(stack.count() > 1)
            throw new IllegalArgumentException("Too many arguments!");

        return stack.pop();
    }

    /**
     * Method for parsing and evaluating input tokens
     * @param token part of expression ( operand, operation)
     * @throws Exception ( Not enough arguments, Invalid token)
     */
    private void process(String token) throws Exception {

        try {
            if(!MapOfOperations.Operations.containsKey(token)) {
                double argument = Double.parseDouble(token);
                stack.push(argument);
            } else {

                Operation op = MapOfOperations.Operations.get(token);
                op.reset();

                try {
                    if (op.arity == 2) {
                        op.setArgument(stack.pop());
                        op.setArgument(stack.pop());
                    } else {
                        op.setArgument(stack.pop());
                    }
                } catch (Exception e) {
                    throw new Exception("Not enough arguments!");
                }

                if(op.readyToCalculate()) {
                    stack.push(op.execute());
                }
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid token found: \"" + token + "\"");
        }

    }

    /**
     * Method for splitting an expression into tokens
     * @param input expression to tokenize
     * @return array with tokens
     */
    private String[] tokenize(String input) {
        return input.split("\\s+");
    }

}
