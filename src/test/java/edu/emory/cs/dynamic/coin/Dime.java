package edu.emory.cs.dynamic.coin;


import edu.emory.cs.dynamic.knapsack.KnapsackItem;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class Dime extends KnapsackItem
{
    public Dime()
    {
        super(2, 10);
    }

    @Override
    public String toString()
    {
        return "Dime";
    }
}