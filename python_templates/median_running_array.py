import heapq


class MedianOfRunningArray:
    def __init__(self):
        self.max_heap = []
        self.min_heap = []

    def insert(self, e):
        if self.min_heap and e < self.min_heap[0]:
            heapq.heappush(self.max_heap, -e)
            if len(self.max_heap) > len(self.min_heap) + 1:
                heapq.heappush(self.min_heap, -heapq.heappop(self.max_heap))
        else:
            heapq.heappush(self.min_heap, e)
            if len(self.min_heap) > len(self.max_heap) + 1:
                heapq.heappush(self.max_heap, -heapq.heappop(self.min_heap))

    def median(self):
        if not self.max_heap and not self.min_heap:
            raise ValueError(
                "Enter at least 1 element, Median of empty list is not defined!"
            )

        if len(self.max_heap) == len(self.min_heap):
            max_top = -self.max_heap[0]
            min_top = self.min_heap[0]
            return (max_top + min_top) / 2

        if len(self.max_heap) > len(self.min_heap):
            return -self.max_heap[0]
        else:
            return self.min_heap[0]
