/*Author : RAJ ROY*/
/*scholar id : 2212002*/

#include <bits/stdc++.h>
using namespace std;

#ifndef ONLINE_JUDGE
#include </home/rkroy/Desktop/code/Debug.h>
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
#pragma comment(linker,"/stack:200000000")
#pragma GCC target("sse,sse2,sse3,ssse3,sse4,popcnt,abm,mmx,avx,tune=native")
#pragma GCC optimize("Ofast,no-stack-protector,no-stack-protector,fast-math")
/*****************************************************************************************************************************************/

void solve() {
	int n;
	cin >> n;
	vector<int> arr(n);
	for (int i = 0; i < n; i++) cin >> arr[i];
	long sum = 0;
	for (int i = 0; i < n; i++) sum += arr[i];
	cout << sum << endl;
	cout << sum + 1 << endl;
	debug(arr);
	return;
}

int main(){
 
   /* #ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin);
    freopen("output2.txt", "w", stdout);
    #endif*/
 
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    int t = 1;
    // int t; cin >> t;
    for(int i = 1; i <= t; i++){
        //cout << "Case #" << i << ":"<< " ";
        solve();
    }
    return 0;
}