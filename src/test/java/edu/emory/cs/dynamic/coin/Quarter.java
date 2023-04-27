package edu.emory.cs.dynamic.coin;


import edu.emory.cs.dynamic.knapsack.KnapsackItem;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class Quarter extends KnapsackItem {
    public Quarter() {
        super(6, 25);
    }

    @Override
    public String toString() {
        return "Quarter";
    }
}