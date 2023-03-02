package edu.emory.cs.sort.hybrid;

import edu.emory.cs.sort.AbstractSort;
import edu.emory.cs.sort.comparison.ShellSortKnuth;
import edu.emory.cs.sort.distribution.LSDRadixSort;
import edu.emory.cs.sort.divide_conquer.IntroSort;
import edu.emory.cs.sort.divide_conquer.MergeSort;
import edu.emory.cs.sort.divide_conquer.QuickSort;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class HybridSortHW<T extends Comparable<T>> implements HybridSort<T> {
    private final AbstractSort<T> engine;

    public HybridSortHW() {
        engine = new IntroSort<T>(new ShellSortKnuth<>());
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] sort(T[][] input) {
        int size = Arrays.stream(input).mapToInt(t -> t.length).sum();
        T[] output = (T[]) Array.newInstance(input[0][0].getClass(), size);
        int beginIndex = 0;
        ArrayList<T[]> result = new ArrayList<>();
        for (T[] t : input) {
            int dir = isSorted(t);
            if(dir == -1) {
                reverse(t);
            } else {
                engine.sort(t);
            }
            result.add(t);
        }

        while(result.size() > 1) {
            T[] t1 = result.remove(0);
            T[] t2 = result.remove(0);
            result.add(merge(t1, t2));
        }

        //engine.sort(output);
        return result.get(0);
    }

    @SuppressWarnings("unchecked")
    public T[] merge(T[] arr1, T[] arr2) {
        T[] output = (T[]) Array.newInstance(arr1[0].getClass(), arr1.length + arr2.length);

        int i = 0;
        int j = 0;
        while(i < arr1.length && j < arr2.length) {
            if(arr1[i].compareTo(arr2[j]) < 0) {
                output[i+j] = arr1[i];
                i++;
            } else {
                output[i+j] = arr2[j];
                j++;
            }
        }

        while(i < arr1.length) {
            output[i + j] = arr1[i];
            i++;
        }

        while(j < arr2.length) {
            output[i + j] = arr2[j];
            j++;
        }

        return output;
    }


    int isSorted(T[] array) {
        int dir = 1;
        if(array[0].compareTo(array[1]) > 0) {
            dir = -1;
        }
        for (int i = 0; i < array.length - 1; ++i) {
            int tempDir = array[i].compareTo(array[i + 1]) > 0 ? -1: 1;
            if(tempDir != dir) {
                return 0;
            }
        }
        return dir;
    }

    void reverse(T[] array) {
        for(int i = 0; i < array.length / 2; i++)
        {
            T temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }
}
