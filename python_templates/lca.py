"""
LCA (Lowest Common Ancestor) class for tree queries using Binary Lifting.

Attributes:
    n (int): Number of nodes in the tree.
    adj (list[list[int]]): Adjacency list representing the tree.
    dp (list[list[int]]): Binary lifting table, dp[u][i] is the 2^i-th ancestor of node u.
    depth (list[int]): Depth of each node from the root (1-indexed).

Methods:
    __init__(n, adj): Initializes the LCA structure and precomputes ancestors and depths.
    dfs(u, par): Depth-first search to fill dp and depth arrays.
    find_kth_parent(u, k): Returns the k-th ancestor of node u.
    get_lca(u, v): Returns the lowest common ancestor of nodes u and v.
    get_distance(u, v): Returns the distance (number of edges) between nodes u and v.
"""


class LCA:
    def __init__(self, n: int, adj: list[list[int]]):
        self.n = n
        self.adj = adj
        self.dp = [[0] * 19 for _ in range(n + 1)]
        self.depth = [0] * (n + 1)
        self.dfs(1, 0)

    def dfs(self, u: int, par: int) -> None:
        """
        Performs DFS to populate dp and depth arrays.
        Args:
            u (int): Current node.
            par (int): Parent of current node.
        """
        self.dp[u][0] = par
        for i in range(1, 18):
            self.dp[u][i] = self.dp[self.dp[u][i - 1]][i - 1]
        for v in self.adj[u]:
            if v != par:
                self.depth[v] = self.depth[u] + 1
                self.dfs(v, u)

    def find_kth_parent(self, u: int, k: int) -> int:
        """
        Finds the k-th ancestor of node u using binary lifting.
        Args:
            u (int): Node to find ancestor for.
            k (int): Number of steps to go up.
        Returns:
            int: The k-th ancestor of u.
        """
        count = 0
        while k > 0:
            if k % 2 == 1:
                u = self.dp[u][count]
            count += 1
            k = k // 2
        return u

    def get_lca(self, u: int, v: int) -> int:
        """
        Finds the lowest common ancestor (LCA) of nodes u and v.
        Args:
            u (int): First node.
            v (int): Second node.
        Returns:
            int: The LCA of u and v.
        """
        if self.depth[u] > self.depth[v]:
            u, v = v, u
        v = self.find_kth_parent(v, (self.depth[v] - self.depth[u]))
        if u == v:
            return u
        for i in range(18, -1, -1):
            if self.dp[u][i] != self.dp[v][i]:
                u = self.dp[u][i]
                v = self.dp[v][i]
        return self.dp[u][0]

    def get_distance(self, u: int, v: int) -> int:
        """
        Computes the distance (number of edges) between nodes u and v.
        Args:
            u (int): First node.
            v (int): Second node.
        Returns:
            int: Distance between u and v.
        """
        return self.depth[u] + self.depth[v] - 2 * self.depth[self.get_lca(u, v)]
