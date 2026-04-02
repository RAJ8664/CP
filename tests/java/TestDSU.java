/*  Simple smoke tests for the DSU class using Java assertions.
    This file is intentionally minimal and uses `assert` statements so it can be
    run without a full build system. Run with `java -ea` to enable assertions.
*/

public class TestDSU {
    public static void main(String[] args) {
        DSU dsu = new DSU(5);
        // initially every node is its own leader
        for (int i = 1; i <= 5; i++) {
            assert dsu.Leader(i) == i;
            assert dsu.getSize(i) == 1;
        }

        // unite some nodes
        dsu.unite(1, 2);
        assert dsu.Is_same_Group(1, 2);
        assert dsu.getSize(1) == 2;

        dsu.unite(3, 4);
        assert dsu.Is_same_Group(3, 4);
        assert dsu.getSize(3) == 2;

        // unite groups
        dsu.unite(2, 3);
        assert dsu.Is_same_Group(1, 4);
        assert dsu.getSize(1) == 4;

        // unite remaining node
        dsu.unite(5, 1);
        assert dsu.getSize(5) == 5;

        System.out.println("TestDSU: all assertions passed");
    }
}
