import math


class Node:
    def __init__(self):
        self.sum = 0
        self.gcd = 0
        self.mini = float("inf")
        self.maxi = float("-inf")


class SegmentTree:
    def __init__(self, n, nums):
        self.n = n
        self.arr = nums[:]
        self.seg = [Node() for _ in range(4 * n + 1)]
        self.build(1, 0, n - 1)

    # convenience wrappers for common operations
    def query_range(self, ql: int, qr: int) -> Node:
        return self.query(1, 0, self.n - 1, ql, qr)

    def update_point(self, pos: int, val: int) -> None:
        self.update(1, 0, self.n - 1, pos, val)

    @staticmethod
    def gcd(x, y):
        while y:
            x, y = y, x % y
        return x

    def merge(self, a, b):
        res = Node()
        res.sum = a.sum + b.sum
        res.mini = min(a.mini, b.mini)
        res.maxi = max(a.maxi, b.maxi)
        res.gcd = self.gcd(a.gcd, b.gcd)
        return res

    def build(self, ind: int, l: int, r: int):
        if l == r:
            self.seg[ind].sum = self.arr[l]
            self.seg[ind].mini = self.arr[l]
            self.seg[ind].maxi = self.arr[l]
            self.seg[ind].gcd = self.arr[l]
            return

        mid = (l + r) // 2
        self.build(2 * ind, l, mid)
        self.build(2 * ind + 1, mid + 1, r)
        self.seg[ind] = self.merge(self.seg[2 * ind], self.seg[2 * ind + 1])

    def update(self, ind: int, l: int, r: int, pos: int, val: int):
        if pos < l or pos > r:
            return
        if l == r:
            self.seg[ind].sum = val
            self.seg[ind].mini = val
            self.seg[ind].maxi = val
            self.seg[ind].gcd = val
            return

        mid = (l + r) // 2
        self.update(2 * ind, l, mid, pos, val)
        self.update(2 * ind + 1, mid + 1, r, pos, val)
        self.seg[ind] = self.merge(self.seg[2 * ind], self.seg[2 * ind + 1])

    def query(self, ind: int, l: int, r: int, ql: int, qr: int):
        if qr < l or ql > r:
            return Node()
        if l >= ql and r <= qr:
            return self.seg[ind]

        mid = (l + r) // 2
        left = self.query(2 * ind, l, mid, ql, qr)
        right = self.query(2 * ind + 1, mid + 1, r, ql, qr)
        return self.merge(left, right)
