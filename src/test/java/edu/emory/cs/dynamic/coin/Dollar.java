package edu.emory.cs.dynamic.coin;


import edu.emory.cs.dynamic.knapsack.KnapsackItem;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class Dollar extends KnapsackItem {
    public Dollar() {
        super(8, 100);
    }

    @Override
    public String toString() {
        return "Dollar";
    }
}