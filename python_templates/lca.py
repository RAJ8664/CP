class LCA:
    def __init__(self, n: int, adj: list[list[int]], root: int = 1):
        self.n = n
        self.adj = adj
        # number of levels needed to cover n nodes
        self.LOG = (n).bit_length() + 1
        self.dp = [[0] * self.LOG for _ in range(n + 1)]
        self.depth = [0] * (n + 1)
        self._dfs(root, 0)

    def _dfs(self, u: int, par: int) -> None:
        self.dp[u][0] = par
        for i in range(1, self.LOG):
            self.dp[u][i] = self.dp[self.dp[u][i - 1]][i - 1]
        for v in self.adj[u]:
            if v != par:
                self.depth[v] = self.depth[u] + 1
                self._dfs(v, u)

    def find_kth_parent(self, u: int, k: int) -> int:
        i = 0
        while k and u:
            if k & 1:
                u = self.dp[u][i]
            k >>= 1
            i += 1
        return u

    def get_lca(self, u: int, v: int) -> int:
        if u == 0 or v == 0:
            return 0
        if self.depth[u] > self.depth[v]:
            u, v = v, u
        diff = self.depth[v] - self.depth[u]
        v = self.find_kth_parent(v, diff)
        if u == v:
            return u
        for i in range(self.LOG - 1, -1, -1):
            if self.dp[u][i] != self.dp[v][i]:
                u = self.dp[u][i]
                v = self.dp[v][i]
        return self.dp[u][0]

    def get_distance(self, u: int, v: int) -> int:
        return self.depth[u] + self.depth[v] - 2 * self.depth[self.get_lca(u, v)]
