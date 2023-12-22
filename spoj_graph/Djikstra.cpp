/*Author : RAJ ROY*/
/*scholar id : 2212002*/

#include <bits/stdc++.h>
using namespace std;
#define endl "\n"

/********************************************** Debug Code **************************************************/
void __print(int x) { cerr << x; }
void __print(long x) { cerr << x; }
void __print(long long x) { cerr << x; }
void __print(unsigned x) { cerr << x; }
void __print(unsigned long x) { cerr << x; }
void __print(unsigned long long x) { cerr << x; }
void __print(float x) { cerr << x; }
void __print(double x) { cerr << x; }
void __print(long double x) { cerr << x; }
void __print(char x) { cerr << '\'' << x << '\''; }
void __print(const char *x) { cerr << '\"' << x << '\"'; }
void __print(const string &x) { cerr << '\"' << x << '\"'; }
void __print(bool x) { cerr << (x ? "true" : "false"); }
template <typename A>
void __print(const A &x);
template <typename A, typename B>
void __print(const pair<A, B> &p);
template <typename... A>
void __print(const tuple<A...> &t);
template <typename T>
void __print(stack<T> s);
template <typename T>
void __print(queue<T> q);
template <typename T, typename... U>
void __print(priority_queue<T, U...> q);
template <typename A>
void __print(const A &x) {
    bool first = true;
    cerr << '{';
    for (const auto &i : x) {
        cerr << (first ? "" : ","), __print(i);
        first = false;
    }
    cerr << '}';
}
template <typename A, typename B>
void __print(const pair<A, B> &p) {
    cerr << '(';
    __print(p.first);
    cerr << ',';
    __print(p.second);
    cerr << ')';
}
template <typename... A>
void __print(const tuple<A...> &t) {
    bool first = true;
    cerr << '(';
    apply([&first](const auto &...args) { ((cerr << (first ? "" : ","), __print(args), first = false), ...); }, t);
    cerr << ')';
}
template <typename T>
void __print(stack<T> s) {
    vector<T> debugVector;
    while (!s.empty()) {
        T t = s.top();
        debugVector.push_back(t);
        s.pop();
    }
    reverse(debugVector.begin(), debugVector.end());
    __print(debugVector);
}
template <typename T>
void __print(queue<T> q) {
    vector<T> debugVector;
    while (!q.empty()) {
        T t = q.front();
        debugVector.push_back(t);
        q.pop();
    }
    __print(debugVector);
}
template <typename T, typename... U>
void __print(priority_queue<T, U...> q) {
    vector<T> debugVector;
    while (!q.empty()) {
        T t = q.top();
        debugVector.push_back(t);
        q.pop();
    }
    __print(debugVector);
}
void _print() { cerr << "]\n"; }
template <typename Head, typename... Tail>
void _print(const Head &H, const Tail &...T) {
    __print(H);
    if (sizeof...(T))
        cerr << ", ";
    _print(T...);
}
#ifndef ONLINE_JUDGE
#define debug(...) cerr << "Line:" << __LINE__ << " [" << #__VA_ARGS__ << "] = ["; _print(__VA_ARGS__)
#else
#define debug(...)
#endif
/***************************************************************************************************************************************************/


int n, m;
const long long INF = 1e17;
const int modb = 1e9+7;
const int MAXN = 1e5+1;
vector<vector<pair<long long,int>>> g(MAXN);
vector<long long> cost(MAXN);
vector<int> route(MAXN);
vector<int> min_f(MAXN);
vector<int> max_f(MAXN);
void dij()
{
	priority_queue< pair<long long,int>, vector<pair<long long,int>>, greater<pair<long long,int>> > pq;
	pq.push({0,1});
	cost[1] = 0;
	route[1] = 1;
	while(!pq.empty())
	{
		long long d = pq.top().first;
		int u = pq.top().second;
		pq.pop();
		if(d > cost[u]) continue;
		for(auto e: g[u])
		{
			int v = e.first;
			long long c = e.second;
			if(c+d > cost[v]) continue;
			if(c+d == cost[v]) 
			{
				route[v] += route[u];
				route[v] %= modb;
				min_f[v] = min(min_f[u]+1, min_f[v]);
				max_f[v] = max(max_f[u]+1, max_f[v]);
			}
			if(c+d < cost[v])
			{
				cost[v] = c+d;
				route[v] = route[u];
				min_f[v] = min_f[u] + 1;
				max_f[v] = max_f[u] + 1;
				pq.push({cost[v],v});
			}
		}
	}
}

void solve() {
	cin >> n >> m;
	for(int i = 0; i <m; ++i)
	{
		int u, v, c;
		cin >> u >> v >> c;
		g[u].push_back({v,c});
	}
	for(int i = 2; i <= n; ++i)
	{
		cost[i] = INF;
	}
	dij();
	//cost array is storing the minimum cost to reach from source(1) to reach destination(n);
	//route array is storing the the number of ways to reach with minimum cost to destination(n);
	//min_f array is storing the the minimum number of edges we can travel to reach to dest(n) with same minimum cost;
	//min_f array is storing the the maximum number of edges we can travel to reach to dest(n) with same minimum cost;
	cout << cost[n] <<" " <<route[n] <<" "<<min_f[n] <<" "<<max_f[n] << endl;
}


int main(){
 
    #ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    #endif
 
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
 
    int t = 1;
    //int t; cin >> t;
    for(int i = 1; i <= t; i++){
        //cout << "Case #" << i << ":"<< " ";
        solve();
    }
    return 0;
}