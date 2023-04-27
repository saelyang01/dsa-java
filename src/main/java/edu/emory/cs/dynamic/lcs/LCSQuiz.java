package edu.emory.cs.dynamic.lcs;


import java.util.*;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class LCSQuiz extends LCSDynamic {
    /**
     * @param a the first string.
     * @param b the second string.
     * @return a set of all longest common sequences between the two strings.
     */
    public Set<String> solveAll(String a, String b) {
        Set<String> set = new HashSet<>();
        int[][] table = createTable(a.toCharArray(), b.toCharArray());
        int max = getMaxValue(table);
        for(int i = 0; i < table.length; i++) {
            if(table[i][table[i].length - 1] == max) {
                helper(a, b, i, table[i].length - 1,  table, "", set);
            }
        }

        for(int i = 0; i < table.length; i++) {
            if(table[table.length - 1][i] == max) {
                helper(a, b, table.length - 1, i,  table, "", set);
            }
        }

        return set;
    }


    /**
     * get max value from a 2d-array
     * @param numbers
     * @return
     */
    private static int getMaxValue(int[][] numbers) {
        int maxValue = numbers[0][0];
        for (int j = 0; j < numbers.length; j++) {
            for (int i = 0; i < numbers[j].length; i++) {
                if (numbers[j][i] > maxValue) {
                    maxValue = numbers[j][i];
                }
            }
        }
        return maxValue;
    }

    /**
     * @param c the first string.
     * @param d the second string.
     * @return the dynamic table populated by estimating the # of LCSs in the grid of the two specific strings.
     */
    protected int[][] createTable(char[] c, char[] d) {
        final int N = c.length, M = d.length;
        int[][] table = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= M; j++)
                table[i][j] = (c[i - 1] == d[j - 1]) ? table[i - 1][j - 1] + 1 : Math.max(table[i - 1][j], table[i][j - 1]);

        return table;
    }
    private static void helper(String s1, String s2, int i, int j, int[][] table, String tempResult, Set<String> set) {
        if (i == 0 || j == 0) {
            set.add(tempResult);
            return;
        }
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            tempResult = s1.charAt(i - 1) + tempResult;
            helper(s1, s2, i-1, j-1, table, tempResult, set);
        } else if (table[i-1][j] > table[i][j-1]) {
            helper(s1, s2, i-1, j, table, tempResult, set);
        } else {
            helper(s1, s2, i, j-1, table, tempResult, set);
        }
    }

    public static void main(String[] args) {
        String s1 = "ABCD";
        String s2 = "ADCB";
        LCSQuiz quiz = new LCSQuiz();
        Set<String> lcsList = quiz.solveAll(s1, s2);
        for(String s: lcsList) {
            System.out.println(s);
        }
    }
}
