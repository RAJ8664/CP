package JAVA_Templates;

import java.util.TreeMap;

@SuppressWarnings("serial")
class CountMap<T> extends TreeMap<T, Integer> {
    CountMap() {}

    CountMap(T[] arr) {
        this.putCM(arr);
    }

    public Integer putCM(T key) {
        if (super.containsKey(key))
            return super.put(key, super.get(key) + 1);
        else
            return super.put(key, 1);
    }

    public Integer removeCM(T key) {
        Integer count = super.get(key);
        if (count == null)
            return -1;
        if (count == 1)
            return super.remove(key);
        else
            return super.put(key, super.get(key) - 1);
    }

    public Integer getCM(T key) {
        Integer count = super.get(key);
        if (count == null)
            return 0;
        return count;
    }

    public void putCM(T[] arr) {
        for (T ele : arr)
            this.putCM(ele);
    }
}

