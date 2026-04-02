class BFPRT:
    @staticmethod
    def get_min_k_nums(arr, k):
        if k < 1 or k > len(arr):
            return None
        min_kth = BFPRT.get_min_kth(arr, k)
        res = []
        for val in arr:
            if val < min_kth:
                res.append(val)
        while len(res) < k:
            res.append(min_kth)
        return res

    @staticmethod
    def get_min_kth(arr, k):
        copy_arr = arr[:]
        return BFPRT.bfprt(copy_arr, 0, len(copy_arr) - 1, k - 1)

    @staticmethod
    def bfprt(arr, begin, end, i):
        if begin == end:
            return arr[begin]
        pivot = BFPRT.median_of_medians(arr, begin, end)
        pivot_range = BFPRT.partition(arr, begin, end, pivot)

        if i >= pivot_range[0] and i <= pivot_range[1]:
            return arr[i]
        elif i < pivot_range[0]:
            return BFPRT.bfprt(arr, begin, pivot_range[0] - 1, i)
        else:
            return BFPRT.bfprt(arr, pivot_range[1] + 1, end, i)

    @staticmethod
    def median_of_medians(arr, begin, end):
        num = end - begin + 1
        offset = 0 if num % 5 == 0 else 1
        m_arr = [0] * (num // 5 + offset)

        for i in range(len(m_arr)):
            m_arr[i] = BFPRT.get_median(arr, begin + i * 5, min(end, begin + i * 5 + 4))

        return BFPRT.bfprt(m_arr, 0, len(m_arr) - 1, len(m_arr) // 2)

    @staticmethod
    def partition(arr, begin, end, num):
        small = begin - 1
        cur = begin
        big = end + 1

        while cur != big:
            if arr[cur] < num:
                small += 1
                arr[small], arr[cur] = arr[cur], arr[small]
                cur += 1
            elif arr[cur] > num:
                big -= 1
                arr[big], arr[cur] = arr[cur], arr[big]
            else:
                cur += 1

        return [small + 1, big - 1]

    @staticmethod
    def get_median(arr, begin, end):
        BFPRT.insertion_sort(arr, begin, end)
        mid = (begin + end) // 2 + ((begin + end) % 2)
        return arr[mid]

    @staticmethod
    def insertion_sort(arr, begin, end):
        if arr is None or len(arr) < 2:
            return
        for i in range(begin + 1, end + 1):
            j = i
            while j != begin:
                if arr[j - 1] > arr[j]:
                    arr[j - 1], arr[j] = arr[j], arr[j - 1]
                    j -= 1
                else:
                    break
