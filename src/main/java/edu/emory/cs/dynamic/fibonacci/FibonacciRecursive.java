package edu.emory.cs.dynamic.fibonacci;

/** @author Jinho D. Choi */
public class FibonacciRecursive implements Fibonacci {
    @Override
    public int get(int k) {
        return switch (k) {
            case 0 -> 0;
            case 1 -> 1;
            default -> get(k - 1) + get(k - 2);
        };
    }
}