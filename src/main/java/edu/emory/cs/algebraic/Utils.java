package edu.emory.cs.algebraic;

public class Utils {
    public static  int getMiddleIndex(int beginIndex, int endIndex) {
        return beginIndex + (endIndex - beginIndex) / 2;
    }

    static public void main(String[] args) {
        System.out.println(getMiddleIndex(0, 10));
    }
}
