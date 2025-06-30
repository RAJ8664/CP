package JAVA_Templates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

class OrderStatisticSet {
    private TreeSet<Integer> set;
    private ArrayList<Integer> sortedList;
    public OrderStatisticSet() {
        set = new TreeSet<>();
        sortedList = new ArrayList<>();
    }
    public boolean add(int x) {
        if (set.add(x)) {
            int idx = Collections.binarySearch(sortedList, x);
            if (idx < 0)
                idx = -idx - 1;
            sortedList.add(idx, x);
            return true;
        }
        return false;
    }
    public boolean remove(int x) {
        if (set.remove(x)) {
            int idx = Collections.binarySearch(sortedList, x);
            if (idx >= 0)
                sortedList.remove(idx);
            return true;
        }
        return false;
    }
    public Integer getKth(int k) {
        if (k < 0 || k >= sortedList.size())
            return null;
        return sortedList.get(k);
    }
    public int size() {
        return set.size();
    }
    public void printSet() {
        System.out.println(sortedList);
    }
}
