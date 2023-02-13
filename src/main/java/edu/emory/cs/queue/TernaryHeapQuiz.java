package edu.emory.cs.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TernaryHeapQuiz<T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private final List<T> keys;

    public TernaryHeapQuiz() {
        this(Comparator.naturalOrder());
    }

    public TernaryHeapQuiz(Comparator<T> priority) {
        super(priority);
        keys = new ArrayList<>();
    }
    /**
     * @param i1 the index of the first key in {@link #keys}.
     * @param i2 the index of the second key in {@link #keys}.
     * @return a negative integer, zero, or a positive integer as the first key is
     * less than, equal to, or greater than the second key.
     */
    private int compare(int i1, int i2) {

        return priority.compare(keys.get(i1), keys.get(i2));
    }

    @Override
    public void add(T key) {
        keys.add(key);
        swim(size() - 1);
    }
    private void swim(int k) {
        int parent = (k == 0) ? -1 : (k - 1 )/ 3;
        while( parent > -1 &&  compare(parent, k) < 0) {
            Collections.swap(keys, parent, k);
            k = parent;
            parent =  (k == 0) ? -1 : (k - 1 )/ 3;
        }


    }

    @Override
    public T remove() {
        if (isEmpty()) return null;
        Collections.swap(keys, 0, size() - 1);
        T max = keys.remove(size() - 1);
        sink();
        return max;
    }

    @Override
    public int size() {
        return keys.size();
    }
    private void sink() {
        if(size() < 2) return;
        T e = keys.get(0);
        int child = getMaxChild(0);
        int k = 0;
        while(child > -1 && compare(child, k) > 0) {
            Collections.swap(keys, child, k);
            k = child;
            child = getMaxChild(k);
        }
    }
    /**
     * get the {position}th child of {i}th element
     * @param i
     * @param position
     * @return
     */
    private int getChild(int i, int position) {
        return 3 * i + 1 + position;
    }

    /**
     * get the max child of {i}th element
     * @param i
     * @return
     */
    private int getMaxChild(int i) {
        int first = getChild(i, 0);
        if(first >= size()) return -1;
        int max = first;
        for(int j = first + 1; j < first + 3 && j < size(); j++) {
            if(compare(max, j) < 0) {
                max = j;
            }
        }
        return max;
    }

}
