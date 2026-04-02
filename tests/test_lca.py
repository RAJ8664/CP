import random

from python_templates.lca import LCA


def naive_lca(u: int, v: int, parent: list[int], depth: list[int]) -> int:
    while depth[u] > depth[v]:
        u = parent[u]
    while depth[v] > depth[u]:
        v = parent[v]
    while u != v:
        u = parent[u]
        v = parent[v]
    return u


def build_adj_from_parents(n: int, parents: list[int]) -> list[list[int]]:
    adj = [[] for _ in range(n + 1)]
    for v in range(1, n + 1):
        p = parents[v]
        if p != 0:
            adj[p].append(v)
            adj[v].append(p)
    return adj


def test_lca_fixed_small():
    # fixed tree:
    # 1
    # ├─2
    # └─3
    #   ├─4
    #   └─5
    n = 5
    parents = [0] * (n + 1)
    parents[1] = 0
    parents[2] = 1
    parents[3] = 1
    parents[4] = 3
    parents[5] = 3
    adj = build_adj_from_parents(n, parents)
    lca = LCA(n, adj, root=1)

    assert lca.get_lca(4, 5) == 3
    assert lca.get_lca(2, 4) == 1
    assert lca.get_lca(1, 5) == 1
    assert lca.get_lca(2, 2) == 2
    assert lca.get_distance(4, 5) == 2
    assert lca.get_distance(2, 4) == 3


def test_lca_random_trees():
    random.seed(42)
    for n in (2, 3, 10, 20):
        parents = [0] * (n + 1)
        parents[1] = 0
        for v in range(2, n + 1):
            parents[v] = random.randint(1, v - 1)
        adj = build_adj_from_parents(n, parents)
        lca = LCA(n, adj, root=1)

        parent = [lca.dp[i][0] for i in range(n + 1)]
        depth = lca.depth

        for _ in range(100):
            u = random.randint(1, n)
            v = random.randint(1, n)
            expect = naive_lca(u, v, parent, depth)
            got = lca.get_lca(u, v)
            assert got == expect, (
                f"LCA mismatch for tree n={n}: nodes {u},{v} -> got {got}, expect {expect}"
            )
            expected_dist = depth[u] + depth[v] - 2 * depth[expect]
            assert lca.get_distance(u, v) == expected_dist
