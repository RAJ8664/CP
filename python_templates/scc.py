class TarjansAlgorithm:
    def __init__(self):
        self.time = 0
        self.scc_list = []

    def strongly_connected_components(self, v, graph):
        low_time = [-1] * v
        insertion_time = [-1] * v
        is_in_stack = [False] * v
        stack = []

        for i in range(v):
            if insertion_time[i] == -1:
                self.scc_util(i, low_time, insertion_time, is_in_stack, stack, graph)

        return self.scc_list

    def scc_util(self, u, low_time, insertion_time, is_in_stack, stack, graph):
        insertion_time[u] = self.time
        low_time[u] = self.time
        self.time += 1

        is_in_stack[u] = True
        stack.append(u)

        for vertex in graph[u]:
            if insertion_time[vertex] == -1:
                self.scc_util(
                    vertex, low_time, insertion_time, is_in_stack, stack, graph
                )
                low_time[u] = min(low_time[u], low_time[vertex])
            elif is_in_stack[vertex]:
                low_time[u] = min(low_time[u], insertion_time[vertex])

        if low_time[u] == insertion_time[u]:
            scc = []
            w = -1
            while w != u:
                w = stack.pop()
                scc.append(w)
                is_in_stack[w] = False
            self.scc_list.append(scc)
