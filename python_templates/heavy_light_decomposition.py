class HeavyLightDecomposition:
    def __init__(self, n):
        self.tree = [[] for _ in range(n + 1)]
        self.parent = [0] * (n + 1)
        self.depth = [0] * (n + 1)
        self.subtree_size = [0] * (n + 1)
        self.chain_head = [-1] * (n + 1)
        self.position = [0] * (n + 1)
        self.node_value = [0] * (n + 1)
        self.segment_tree = [0] * (4 * (n + 1))
        self.position_index = 0

    def add_edge(self, u, v):
        self.tree[u].append(v)
        self.tree[v].append(u)

    def dfs_size(self, node, parent_node):
        self.parent[node] = parent_node
        self.subtree_size[node] = 1

        for child in self.tree[node]:
            if child != parent_node:
                self.depth[child] = self.depth[node] + 1
                self.dfs_size(child, node)
                self.subtree_size[node] += self.subtree_size[child]

    def decompose(self, node, head):
        self.chain_head[node] = head
        self.position[node] = self.position_index
        self.position_index += 1

        heavy_child = -1
        max_subtree = -1

        for child in self.tree[node]:
            if child != self.parent[node] and self.subtree_size[child] > max_subtree:
                heavy_child = child
                max_subtree = self.subtree_size[child]

        if heavy_child != -1:
            self.decompose(heavy_child, head)

        for child in self.tree[node]:
            if child != self.parent[node] and child != heavy_child:
                self.decompose(child, child)

    def build_segment_tree(self, node, start, end):
        if start == end:
            self.segment_tree[node] = self.node_value[start]
            return

        mid = (start + end) // 2
        self.build_segment_tree(2 * node, start, mid)
        self.build_segment_tree(2 * node + 1, mid + 1, end)
        self.segment_tree[node] = max(
            self.segment_tree[2 * node], self.segment_tree[2 * node + 1]
        )

    def update_segment_tree(self, node, start, end, index, value):
        if start == end:
            self.segment_tree[node] = value
            return

        mid = (start + end) // 2
        if index <= mid:
            self.update_segment_tree(2 * node, start, mid, index, value)
        else:
            self.update_segment_tree(2 * node + 1, mid + 1, end, index, value)

        self.segment_tree[node] = max(
            self.segment_tree[2 * node], self.segment_tree[2 * node + 1]
        )

    def query_segment_tree(self, node, start, end, left, right):
        if left > end or right < start:
            return float("-inf")
        if left <= start and end <= right:
            return self.segment_tree[node]

        mid = (start + end) // 2
        left_query = self.query_segment_tree(2 * node, start, mid, left, right)
        right_query = self.query_segment_tree(2 * node + 1, mid + 1, end, left, right)
        return max(left_query, right_query)

    def query_max_in_path(self, u, v):
        result = float("-inf")

        while self.chain_head[u] != self.chain_head[v]:
            if self.depth[self.chain_head[u]] < self.depth[self.chain_head[v]]:
                u, v = v, u

            result = max(
                result,
                self.query_segment_tree(
                    1,
                    0,
                    self.position_index - 1,
                    self.position[self.chain_head[u]],
                    self.position[u],
                ),
            )
            u = self.parent[self.chain_head[u]]

        if self.depth[u] > self.depth[v]:
            u, v = v, u

        result = max(
            result,
            self.query_segment_tree(
                1, 0, self.position_index - 1, self.position[u], self.position[v]
            ),
        )
        return result

    def initialize(self, root, values):
        self.dfs_size(root, -1)
        self.decompose(root, root)
        for i in range(len(values)):
            self.node_value[self.position[i]] = values[i]
        self.build_segment_tree(1, 0, self.position_index - 1)
