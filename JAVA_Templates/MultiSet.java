package JAVA_Templates;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

class MultiSet<T> {
    TreeMap<T, Integer> frequency;
    TreeSet<T> set;
    int size;

    public MultiSet() {
        set = new TreeSet<>();
        frequency = new TreeMap<>();
        size = 0;
    }

    public MultiSet(Comparator<T> cmp) {
        set = new TreeSet<>(cmp);
        frequency = new TreeMap<>(cmp);
        size = 0;
    }

    public void add(T elem) {
        if (frequency.get(elem) == null || frequency.get(elem) == 0) {
            frequency.put(elem, 0);
            set.add(elem);
        }
        frequency.put(elem, frequency.get(elem) + 1);
        size++;
    }

    public void remove(T elem) {
        if (!set.contains(elem))
            return;
        frequency.put(elem, frequency.get(elem) - 1);
        if (frequency.get(elem) == 0) {
            set.remove(elem);
            frequency.remove(elem);
        }
        size--;
    }

    public boolean contains(T elem) {
        return set.contains(elem);
    }

    @Override
    public String toString() {
        String current = "(";
        for (T ele : set) {
            int freq = frequency.get(ele);
            for (int i = 0; i < freq; i++) {
                if (current.length() == 1)
                    current += ele;
                else
                    current += "," + ele;
            }
        }
        current += ")";
        return current;
    }

    // Returns the count of the specified element in this muresiset
    public int count(T element) {
        return frequency.getOrDefault(element, 0);
    }

    // Returns the total number of elements in the muresiset (including duplicates)
    public int size() {
        int size = 0;
        for (int count : frequency.values())
            size += count;
        return size;
    }

    // Returns the smallest element in this muresiset greater than or equal to the
    // given
    // element, or null if there is no such element
    public T ceiling(T element) {
        return frequency.ceilingKey(element);
    }

    // Returns the greatest element in this muresiset less than or equal to the
    // given element,
    // or null if there is no such element
    public T floor(T element) {
        return frequency.floorKey(element);
    }

    // Returns the smallest element in this muresiset strictly greater than the
    // given element,
    // or null if there is no such element
    public T higher(T element) {
        return frequency.higherKey(element);
    }

    // Returns the greatest element in this muresiset strictly less than the given
    // element, or
    // null if there is no such element
    public T lower(T element) {
        return frequency.lowerKey(element);
    }
}

