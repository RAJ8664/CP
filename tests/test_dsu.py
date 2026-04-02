import sys
import os

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), "..")))

from python_templates.dsu import DSU


def test_dsu_union_and_leader():
    d = DSU(5)
    assert d.number_of_groups == 5
    d.unit(1, 2)
    assert d.is_same_group(1, 2)
    assert d.get_size(1) == 2
    d.unit(3, 4)
    assert d.number_of_groups == 3
    d.unit(2, 3)
    assert d.get_size(1) == 4
