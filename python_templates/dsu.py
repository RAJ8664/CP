class DSU:
    def __init__(self, n: int):
        self.n = n
        self.parent = [0] * (n + 1)
        self.group_size = [1] * (n + 1)
        self.number_of_nodes = n
        self.number_of_groups = n
        self.max_group = 1
        for i in range(1, n + 1):
            self.parent[i] = i

    def leader(self, x: int) -> int:
        if self.parent[x] == x:
            return x
        self.parent[x] = self.leader(self.parent[x])
        return self.parent[x]

    def is_same_group(self, u: int, v: int) -> bool:
        return self.leader(u) == self.leader(v)

    def unit(self, u: int, v: int) -> None:
        leader1 = self.leader(u)
        leader2 = self.leader(v)
        if leader1 != leader2:
            self.number_of_groups -= 1
            if self.group_size[leader1] < self.group_size[leader2]:
                temp = leader1
                leader1 = leader2
                leader2 = temp
            self.parent[leader2] = leader1
            self.group_size[leader1] += self.group_size[leader2]
            self.max_group = max(self.max_group, self.group_size[leader1])

    def get_size(self, x: int) -> int:
        return self.group_size[self.leader(x)]
