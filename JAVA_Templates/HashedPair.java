package JAVA_Templates;

import java.util.Objects;

class Unique_Pair {
    int first;
    int second;

    Unique_Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Unique_Pair current = (Unique_Pair)(o);
        return first == current.first && second == current.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "(" + first + " " + second + ")";
    }
}


