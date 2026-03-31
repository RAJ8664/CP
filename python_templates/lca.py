class LCA:
    def __init__(self, n: int, adj: list[list[int]]):
        self.n = n
        self.adj = adj
        self.dp = [[0] * 19 for _ in range(n + 1)]
        self.depth = [0] * (n + 1)
        self.dfs(1, 0)

    def dfs(self, u: int, par: int) -> None:
        self.dp[u][0] = par
        for i in range(1, 18):
            self.dp[u][i] = self.dp[self.dp[u][i - 1]][i - 1]
        for v in self.adj[u]:
            if v != par:
                self.depth[v] = self.depth[u] + 1
                self.dfs(v, u)

    def find_kth_parent(self, u: int, k: int) -> int:
        count = 0
        while k > 0:
            if k % 2 == 1:
                u = self.dp[u][count]
            count += 1
            k = k // 2
        return u

    def get_lca(self, u: int, v: int) -> int:
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
        return self.depth[u] + self.depth[v] - 2 * self.depth[self.get_lca(u, v)]
