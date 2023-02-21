package edu.emory.cs.sort.comparison;

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
        // TODO: to be filled
    }

    @Override
    protected int getSequenceStartIndex(int n) {
        // TODO: to be filled
        return -1;
    }
}