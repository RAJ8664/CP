import sys
import os

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), "..")))

from python_templates.segment_tree import SegmentTree


def test_segment_sum_and_update():
    arr = [1, 2, 3, 4, 5]
    st = SegmentTree(len(arr), arr)
    # sum [1..3] = 2+3+4 = 9 (0-indexed)
    node = st.query_range(1, 3)
    assert node.sum == 9
    # update position 2 (value 3 -> 10)
    st.update_point(2, 10)
    node2 = st.query_range(1, 3)
    assert node2.sum == 16
