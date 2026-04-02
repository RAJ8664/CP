class TrieNode:
    def __init__(self, value="*"):
        self.value = value
        self.child = {}
        self.end = False


class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        current = self.root
        for c in word:
            if c not in current.child:
                current.child[c] = TrieNode(c)
            current = current.child[c]
        current.end = True

    def search(self, word):
        current = self.root
        for c in word:
            if c not in current.child:
                return False
            current = current.child[c]
        return current.end

    def delete(self, word):
        current = self.root
        for c in word:
            if c not in current.child:
                return False
            current = current.child[c]

        if current.end:
            current.end = False
            return True
        return False

    def count_words(self):
        return self._count_words_helper(self.root)

    def _count_words_helper(self, node):
        if node is None:
            return 0

        count = 1 if node.end else 0
        for child in node.child.values():
            count += self._count_words_helper(child)
        return count

    def starts_with_prefix(self, prefix):
        current = self.root
        for c in prefix:
            if c not in current.child:
                return False
            current = current.child[c]
        return True

    def count_words_with_prefix(self, prefix):
        current = self.root
        for c in prefix:
            if c not in current.child:
                return 0
            current = current.child[c]
        return self._count_words_helper(current)
