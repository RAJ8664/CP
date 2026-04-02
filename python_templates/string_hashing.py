import random


class Hashing:
    MULTIPLIER = 43
    MOD1 = 1000000007
    MOD2 = 1000000009

    def __init__(self, s):
        self.n = len(s)
        self.hash1 = [0] * (self.n + 1)
        self.hash2 = [0] * (self.n + 1)
        self.inv1 = [0] * (self.n + 1)
        self.inv2 = [0] * (self.n + 1)
        self.inv1[0] = 1
        self.inv2[0] = 1

        inv_multiplier1 = pow(self.MULTIPLIER, self.MOD1 - 2, self.MOD1)
        inv_multiplier2 = pow(self.MULTIPLIER, self.MOD2 - 2, self.MOD2)

        p1 = 1
        p2 = 1

        for i in range(self.n):
            self.hash1[i + 1] = (self.hash1[i] + ord(s[i]) * p1) % self.MOD1
            p1 = p1 * self.MULTIPLIER % self.MOD1
            self.inv1[i + 1] = self.inv1[i] * inv_multiplier1 % self.MOD1

            self.hash2[i + 1] = (self.hash2[i] + ord(s[i]) * p2) % self.MOD2
            p2 = p2 * self.MULTIPLIER % self.MOD2
            self.inv2[i + 1] = self.inv2[i] * inv_multiplier2 % self.MOD2

    def get_hash(self, i, length):
        h1 = (
            (self.hash1[i + length] - self.hash1[i] + self.MOD1) * self.inv1[i]
        ) % self.MOD1
        h2 = (
            (self.hash2[i + length] - self.hash2[i] + self.MOD2) * self.inv2[i]
        ) % self.MOD2
        return (h1 << 32) + h2

    def get_hash_bounds(self, x, y):
        return self.get_hash(x, y - x + 1)
