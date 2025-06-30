package JAVA_Templates;

import java.util.Collection;
import java.util.TreeMap;

class MultiTreeSet<E> {
    TreeMap<E, Integer> freqTreeMap = new TreeMap<E, Integer>();
    int size;

    public MultiTreeSet() {}

    public MultiTreeSet(Collection<? extends E> c) {
        for (E element : c)
            add(element);
    }

    public int size() {
        return size;
    }

    public void add(E element) {
        Integer freq = freqTreeMap.get(element);
        if (freq == null)
            freqTreeMap.put(element, 1);
        else
            freqTreeMap.put(element, freq + 1);
        ++size;
    }

    public void remove(E element) {
        Integer freq = freqTreeMap.get(element);
        if (freq != null) {
            if (freq == 1)
                freqTreeMap.remove(element);
            else
                freqTreeMap.put(element, freq - 1);
            --size;
        }
    }

    public int get(E element) {
        Integer freq = freqTreeMap.get(element);
        if (freq == null)
            return 0;
        return freq;
    }

    public boolean contains(E element) {
        return get(element) > 0;
    }

    @Override
    public String toString() {
        String current = "( ";
        for (E ele : freqTreeMap.keySet()) {
            int freq = freqTreeMap.get(ele);
            for (int i = 0; i < freq; i++)
                current += ele + " ";
        }
        current += ")";
        return current;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        return freqTreeMap.firstKey();
    }

    public E last() {
        return freqTreeMap.lastKey();
    }

    public E ceiling(E element) {
        return freqTreeMap.ceilingKey(element);
    }

    public E floor(E element) {
        return freqTreeMap.floorKey(element);
    }

    public E higher(E element) {
        return freqTreeMap.higherKey(element);
    }

    public E lower(E element) {
        return freqTreeMap.lowerKey(element);
    }
}

