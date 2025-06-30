package JAVA_Templates;

class DSU {
    int[] Parent, Group_Size;
    int Number_of_Nodes, Number_of_Groups, Max_Group;

    public DSU(int Number_of_Nodes) {
        this.Number_of_Nodes = Number_of_Nodes;
        Parent = new int[Number_of_Nodes + 1];
        Group_Size = new int[Number_of_Nodes + 1];
        Number_of_Groups = Number_of_Nodes;
        Max_Group = 1;
        for (int i = 1; i <= Number_of_Nodes; i++) {
            Parent[i] = i;
            Group_Size[i] = 1;
        }
    }

    public int Leader(int x) {
        return Parent[x] = (Parent[x] == x ? x : Leader(Parent[x]));
    }

    public boolean Is_same_Group(int x, int y) {
        return Leader(x) == Leader(y);
    }

    public void unite(int x, int y) {
        int leader1 = Leader(x);
        int leader2 = Leader(y);
        if (leader1 != leader2) {
            Number_of_Groups--;
            if (Group_Size[leader1] < Group_Size[leader2]) {
                int temp = leader1;
                leader1 = leader2;
                leader2 = temp;
            }
            Parent[leader2] = leader1;
            Group_Size[leader1] += Group_Size[leader2];
            Max_Group = Math.max(Max_Group, Group_Size[leader1]);
        }
    }

    public int getSize(int x) {
        return Group_Size[Leader(x)];
    }
}


