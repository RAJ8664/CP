
import java.util.*;
public class print_connected_component{
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for(int i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}

		for(int i = 0; i < m; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			adj.get(u).add(v);
			adj.get(v).add(u);
		}

		int vis[] = new int[n + 1];
		Arrays.fill(vis, 0);
		ArrayList<Integer> current = new ArrayList<>();
		ArrayList<ArrayList<Integer>> cc = new ArrayList<>();
		for(int i = 1; i <= n; i++) {
			if(vis[i] == 0) {
				current.clear();
				dfs(i, vis,adj,current);
				cc.add(new ArrayList<>(current));
			}
		}
		int len = cc.size();
		System.out.println(len);
		for(ArrayList<Integer> temp : cc) {
			for(int i = 0; i < temp.size(); i++) {
				System.out.print(temp.get(i) + " ");
			}
			System.out.println();
		}
	}

	public static void dfs(int u, int vis[], ArrayList<ArrayList<Integer>> adj,ArrayList<Integer> current) {
		vis[u] = 1;
		current.add(u);
		for(int child : adj.get(u)) {
			if(vis[child] == 0) dfs(child, vis, adj,current);
		}
	}
}