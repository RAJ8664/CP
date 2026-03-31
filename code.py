from python_templates.lca import LCA

n = int(input())
adj: list[list[int]] = [[] for _ in range(n + 1)]

for _ in range(n - 1):
    u, v = map(int, input().split())
    adj[u].append(v)
    adj[v].append(u)

lca_solver = LCA(n, adj)

print(lca_solver.get_lca(2, 3))
