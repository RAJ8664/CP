class BipartiteGraph:
    @staticmethod
    def is_bipartite(v, adj):
        color = [-1] * (v + 1)

        def bipartite(node):
            if color[node] == -1:
                color[node] = 1

            for neighbor in adj[node]:
                if color[neighbor] == -1:
                    color[neighbor] = 1 - color[node]
                    if not bipartite(neighbor):
                        return False
                elif color[neighbor] == color[node]:
                    return False
            return True

        for i in range(v):
            if color[i] == -1:
                if not bipartite(i):
                    return False
        return True
