import java.util.*;

public class Debugger {
    public static void print(Object dataStructure) {
        if (dataStructure instanceof Collection) {
            printCollection((Collection<?>) dataStructure);
        } else if (dataStructure instanceof int[][]) {
            printMatrix((int[][]) dataStructure);
        }

        else if (dataStructure instanceof Map) {
            printMap((Map<?, ?>) dataStructure);
        } else if (dataStructure.getClass().isArray()) {
            printArray(dataStructure);
        } else if (dataStructure instanceof Queue) {
            printQueue((Queue<?>) dataStructure);
        } else if (dataStructure instanceof Stack) {
            printStack((Stack<?>) dataStructure);
        } else {
            System.out.print(dataStructure + " ");
        }
    }

    public static void printCollection(Collection<?> collection) {
        System.out.print("Collection: ");
        for (Object item : collection) {
            print(item);
        }
    }

    public static void printMap(Map<?, ?> map) {
        System.out.print("Map: ");
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.print("(");
            print(entry.getKey());
            System.out.print("->");
            print(entry.getValue());
            System.out.print(") ");
        }
    }

    public static void printArray(Object array) {
        System.out.print("Array: [");
        int length = java.lang.reflect.Array.getLength(array);
        for (int i = 0; i < length; i++) {
            print(java.lang.reflect.Array.get(array, i));
            if (i < length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("] ");
    }

    public static void printQueue(Queue<?> queue) {
        System.out.print("Queue: [");
        for (Object item : queue) {
            print(item);
            System.out.print(" ");
        }
        System.out.print("] ");
    }

    public static void printStack(Stack<?> stack) {
        System.out.print("Stack: [");
        for (Object item : stack) {
            print(item);
            System.out.print(" ");
        }
        System.out.print("] ");
    }
    public static void printMatrix(int[][] matrix) {
        System.out.println("Matrix: ");
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printArrayList(ArrayList<Integer> ans){
        System.out.print("ArrayList : ");
        for(int i = 0; i < ans.size(); i++){
            print(ans.get(i) + " ");
        }
        System.out.println();
    }

    public static void printArrayListofArrayList(ArrayList<ArrayList<Integer>> ans){
        System.out.print("ArrayList: ");
        for(ArrayList<Integer> current : ans){
            for(int i = 0; i < current.size(); i++){
                print(current.get(i) + " ");
            }
            System.out.println();
        }
    }
}
