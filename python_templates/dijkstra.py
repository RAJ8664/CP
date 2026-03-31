import heapq


class Pair:
    def __init__(self, node: int, weight: int):
        self.node = node
        self.weight = weight

    def __repr__(self):
        return f"({self.node}, {self.weight})"

    def __lt__(self, other):
        return self.weight < other.weight


class DJ:
    def __init__(self, n: int, edges: list[list[int]]):
        self.n = n
        self.edges = edges
        self.adj = [[] for _ in range(n + 1)]
        for u, v, wt in edges:
            self.adj[u].append(Pair(v, wt))
            self.adj[v].append(Pair(u, wt))

    def get_min_distance(self, source: int, destination: int) -> int:
        pq = []
        dist = [float("inf")] * (self.n + 1)
        dist[source] = 0
        heapq.heappush(pq, Pair(source, 0))
        while pq:
            curr_pair = heapq.heappop(pq)
            curr_node, curr_dist = curr_pair.node, curr_pair.weight
            if curr_dist > dist[curr_node]:
                continue
            for pair in self.adj[curr_node]:
                child_node, child_dist = pair.node, pair.weight
                if dist[child_node] > curr_dist + child_dist:
                    dist[child_node] = child_dist + curr_dist
                    heapq.heappush(pq, Pair(child_node, dist[child_node]))

        if dist[destination] == float("inf"):
            return -1
        return int(dist[destination])
