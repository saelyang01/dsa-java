package edu.emory.cs.sort.comparison;

import java.util.Arrays;
import java.util.Comparator;


public class ShellSortQuiz<T extends Comparable<T>> extends ShellSort<T> {
    public ShellSortQuiz() {
        this(Comparator.naturalOrder());
    }

    public ShellSortQuiz(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    protected void populateSequence(int n) {
        sequence.clear();
        int i = 1;
        int index = (int)Math.pow(2, i) - 1;
        while(index < n) {
            sequence.add(index);
            i++;
            index = (int)Math.pow(2, i) - 1;
        }
        //System.out.println(n);
        //    System.out.println(Arrays.toString(sequence.toArray(new Integer[0])));
    }

    @Override
    protected int getSequenceStartIndex(int n) {
        return sequence.size() - 1;
    }
}
