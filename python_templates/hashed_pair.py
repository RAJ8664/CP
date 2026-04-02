class HashedPair:
    def __init__(self, first, second):
        self.first = first
        self.second = second

    def __eq__(self, other):
        if not isinstance(other, HashedPair):
            return False
        return self.first == other.first and self.second == other.second

    def __hash__(self):
        return hash((self.first, self.second))

    def __str__(self):
        return f"({self.first} {self.second})"

    def __repr__(self):
        return self.__str__()
