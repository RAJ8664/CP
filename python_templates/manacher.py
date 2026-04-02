class Manacher:
    @staticmethod
    def longest_palindrome(s):
        if not s:
            return ""

        processed = Manacher.preprocess(s)
        palindrome_lengths = [0] * len(processed)
        center = 0
        right_boundary = 0
        max_len = 0
        center_index = 0

        for i in range(1, len(processed) - 1):
            mirror = 2 * center - i

            if i < right_boundary:
                palindrome_lengths[i] = min(
                    right_boundary - i, palindrome_lengths[mirror]
                )

            while (
                processed[i + 1 + palindrome_lengths[i]]
                == processed[i - 1 - palindrome_lengths[i]]
            ):
                palindrome_lengths[i] += 1

            if i + palindrome_lengths[i] > right_boundary:
                center = i
                right_boundary = i + palindrome_lengths[i]

            if palindrome_lengths[i] > max_len:
                max_len = palindrome_lengths[i]
                center_index = i

        start = (center_index - max_len) // 2
        return s[start : start + max_len]

    @staticmethod
    def preprocess(s):
        if not s:
            return "^$"
        result = "^"
        for c in s:
            result += "#" + c
        result += "#$"
        return result
