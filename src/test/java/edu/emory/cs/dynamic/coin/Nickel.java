package edu.emory.cs.dynamic.coin;


import edu.emory.cs.dynamic.knapsack.KnapsackItem;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class Nickel extends KnapsackItem {
    public Nickel() {
        super(5, 5);
    }

    @Override
    public String toString() {
        return "Nickel";
    }
}