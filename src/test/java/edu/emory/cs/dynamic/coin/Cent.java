package edu.emory.cs.dynamic.coin;


import edu.emory.cs.dynamic.knapsack.KnapsackItem;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class Cent extends KnapsackItem {
    public Cent() {
        super(3, 1);
    }

    @Override
    public String toString() {
        return "Cent";
    }
}