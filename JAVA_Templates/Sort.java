package JAVA_Templates;

import java.util.ArrayList;
import java.util.Collections;

class Sort {
    public static void sort(int[] arr) {
        // because Arrays.sort() uses quicksort which is dumb
        // Collections.sort() uses merge sort
        ArrayList<Integer> ls = new ArrayList<>();
        for (Integer x : arr)
            ls.add(x);
        Collections.sort(ls);
        for (int i = 0; i < arr.length; i++)
            arr[i] = ls.get(i);
    }

    public static void sort(long[] arr) {
        // because Arrays.sort() uses quicksort which is dumb
        // Collections.sort() uses merge sort
        ArrayList<Long> ls = new ArrayList<>();
        for (Long x : arr)
            ls.add(x);
        Collections.sort(ls);
        for (int i = 0; i < arr.length; i++)
            arr[i] = ls.get(i);
    }
}
