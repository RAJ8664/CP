from math import log2


class SparseTableMin:
    def __init__(self, arr: list[int]) -> None:
        self.arr = arr
        self.sparse_table = self.build_sparse_table(arr)

    def build_sparse_table(self, arr: list[int]) -> list[list[int]]:
        if not arr:
            raise ValueError("empty number list not allowed")

        length = len(arr)

        row = int(log2(length)) + 1
        sparse_table = [[0 for i in range(length)] for j in range(row)]

        for i, value in enumerate(arr):
            sparse_table[0][i] = value
        j = 1
        while (1 << j) <= length:
            i = 0
            while (i + (1 << j) - 1) < length:
                sparse_table[j][i] = min(
                    sparse_table[j - 1][i + (1 << (j - 1))], sparse_table[j - 1][i]
                )
                i += 1
            j += 1
        return sparse_table

    def query_min(self, left_bound: int, right_bound: int) -> int:
        if left_bound < 0 or right_bound >= len(self.sparse_table[0]):
            raise IndexError("list index out of range")
        j = int(log2(right_bound - left_bound + 1))
        return min(
            self.sparse_table[j][right_bound - (1 << j) + 1],
            self.sparse_table[j][left_bound],
        )
