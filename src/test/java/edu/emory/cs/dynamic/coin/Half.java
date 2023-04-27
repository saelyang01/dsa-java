package edu.emory.cs.dynamic.coin;


import edu.emory.cs.dynamic.knapsack.KnapsackItem;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class Half extends KnapsackItem {
    public Half() {
        super(11, 50);
    }

    @Override
    public String toString() {
        return "Half";
    }
}
