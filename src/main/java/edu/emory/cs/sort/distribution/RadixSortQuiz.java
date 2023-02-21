package edu.emory.cs.sort.distribution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;


public class RadixSortQuiz extends RadixSort {
    int bit;
    public RadixSortQuiz() {
        super();
    }

    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex) {
        int maxBit = getMaxBit(array, beginIndex, endIndex);

        int div = (int)Math.pow(10, maxBit - 1);
        for (int i = beginIndex; i < endIndex; i++)
            buckets.get((array[i] / div) % 10).add(array[i]);
        for(int i = 0; i < buckets.size(); i++) {
            helper(buckets.get(i), maxBit  - 1);
        }

        int index = 0;
        for(int i = 0; i < buckets.size(); i++) {

            while(!buckets.get(i).isEmpty()) {
                array[index++] = buckets.get(i).remove();
            }
        }


    }

    private void helper(Deque<Integer> array, int bit) {
        if(bit == 0) {
            RadixSortQuiz temp = new RadixSortQuiz();
            for (Integer t : array) {
                temp.buckets.get(t  % 10).add(t);
            }
            array.clear();
            int i = 0;
            for (Deque<Integer> bucket : temp.buckets) {
                while (!bucket.isEmpty())
                    array.add(bucket.remove());
            }

        } else {
            int div = (int)Math.pow(10, bit);
            RadixSortQuiz temp = new RadixSortQuiz();
            //System.out.println("Before: " + array.size());
            for (Integer t : array) {
                //System.out.print(t + ", ");
                temp.buckets.get((t / div) % 10).add(t);
            }
            //System.out.println();
            array.clear();
            ArrayList<Integer> list  = new ArrayList<>();
            for(int i = 0; i < temp.buckets.size(); i++) {
                helper(temp.buckets.get(i), bit - 1);

                for(Integer integer: temp.buckets.get(i)) {
                    //System.out.print(integer + ", ");
                    array.add(integer);
                }
            }
            //System.out.println("after: " + array.size());
        }

    }

}