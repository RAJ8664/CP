/*Author : RAJ ROY*/

#include <bits/stdc++.h>
using namespace std;


class DRang_Sum{
	vector<long long> seg;
public:
	DRang_Sum(int n){
		seg.resize(4 * n + 5);
	}

	void point_update(int arr[],int ind, int low, int high , int index, int val){
		if(low == high){
			seg[ind] = val;
			return;
		}

		int mid = low + (high - low) / 2;
		if(index <= mid) point_update(arr, 2 * ind + 1, low, mid, index, val);
		else point_update(arr,2 * ind + 2, mid + 1, high , index, val);
		seg[ind] = (long long)(seg[2 * ind + 1] + seg[2 * ind + 2]);
	}

	long long query(int arr[], int ind, int low, int high , int l , int r){
		if(low >= l && high <= r) return seg[ind];
		if(low > r || high < l) return 0;
		int mid = low + (high - low) / 2;
		long long left = query(arr, 2 * ind + 1, low , mid, l , r);
		long long right = query(arr, 2 * ind + 2, mid + 1, high , l ,r);
		return (long long)(left + right);
	}

	void build(int arr[],int ind , int low, int high){
		if(low == high){
			seg[ind] = arr[low];
			return;
		}

		int mid = low + (high - low) / 2;
		build(arr, 2 * ind + 1, low, mid);
		build(arr, 2 * ind + 2, mid + 1, high);
		seg[ind] = (long long)(seg[2 * ind + 1] +  seg[2 * ind + 2]);
	}
};

int main(){
	int n , q;
	cin >> n >> q;
	int arr[n];
	for(int i = 0; i < n; i++){
		int x;
		cin >> x;
		arr[i] = x;
	}

	DRang_Sum s1(n);
	s1.build(arr,0, 0, n - 1);
	for(int i = 0; i < q; i++){
		int type;
		cin >> type;
		if(type == 1){
			int index, val;
			cin >> index >> val;
			index--;
			s1.point_update(arr, 0, 0,n -  1, index, val);

		}
		else {
			int l , r;
			cin >> l >> r;
			--l;
			--r;
			cout << s1.query(arr, 0 , 0 , n - 1, l, r) << endl;
		}
	}

}