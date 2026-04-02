class StringCompression:
    @staticmethod
    def compress(input_str):
        if len(input_str) == 1:
            return input_str[0]

        count = 1
        compressed = ""

        for i in range(len(input_str) - 1):
            if input_str[i] == input_str[i + 1]:
                count += 1

            if (i + 1) == len(input_str) - 1 and input_str[i + 1] == input_str[i]:
                compressed = StringCompression.append_count(
                    compressed, count, input_str[i]
                )
                break
            elif input_str[i] != input_str[i + 1]:
                if (i + 1) == len(input_str) - 1:
                    compressed = (
                        StringCompression.append_count(compressed, count, input_str[i])
                        + input_str[i + 1]
                    )
                    break
                else:
                    compressed = StringCompression.append_count(
                        compressed, count, input_str[i]
                    )
                    count = 1

        return compressed

    @staticmethod
    def append_count(res, count, ch):
        if count > 1:
            res += ch + str(count)
        else:
            res += ch
        return res
