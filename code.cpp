/*Author : RAJ ROY*/
/*scholar id : 2212002*/

#include <bits/stdc++.h>
using namespace std;

#ifndef ONLINE_JUDGE
#include "Debug.h"
#else
#define debug(...) 42
#endif

#define endl "\n"
#define ll long long
#pragma GCC optimize("O1")
#pragma GCC optimize("O2")
#pragma GCC optimize("Ofast")
#pragma GCC target("avx,avx2,fma")
#pragma GCC optimize("O3,unroll-loops")
#pragma GCC target("sse,sse2,sse3,ssse3,sse4,popcnt,abm,mmx,avx,tune=native")
#pragma GCC optimize("Ofast,no-stack-protector,no-stack-protector,fast-math")
/*****************************************************************************************************************************************/

unordered_map<long long, int> firstSum, secondSum;

void build(int ind, vector<int> &arr, long long sum, int id) {
    if (ind >= (int)arr.size()) {
        if (id == 1)
            firstSum[sum] += 1;
        else
            secondSum[sum] += 1;
        return;
    }
    build(ind + 1, arr, sum + arr[ind], id);
    build(ind + 1, arr, sum, id);
}

void solve() {
    int n, k;
    cin >> n >> k;

    vector<int> arr(n);
    for (int i = 0; i < n; i++)
        cin >> arr[i];

    vector<int> first(arr.begin(), arr.begin() + n / 2);
    vector<int> second(arr.begin() + n / 2, arr.end());

    firstSum.clear();
    secondSum.clear();

    build(0, first, 0, 1);
    build(0, second, 0, 2);

    long long count = 0;
    for (auto &curr : firstSum) {
        long long key = curr.first;
        long long req = (long long)k - key;
        if (secondSum.count(req)) {
            count += 1LL * curr.second * secondSum[req];
        }
    }
    cout << count << "\n";
}
int main() {

#ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
#endif

    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int t = 1;
    // int t;
    // cin >> t;
    for (int i = 1; i <= t; i++) {
        // cout << "Case #" << i << ":"<< " ";
        solve();
    }
    return 0;
}
