package com.coursera;

import java.util.ArrayList;
import java.util.Scanner;

public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        boolean[] visited = new boolean[adj.length];
        return dfs(visited, adj, x, y);
    }

    static int dfs(boolean[] visited, ArrayList<Integer>[] adj, int x, int y) {
        if (x == y) {
            return 1;
        }
        if (visited[x]) {
            return 0;
        }
        visited[x] = true;
        int res = 0;
        for (int v: adj[x]) {
            res += dfs(visited, adj, v, y);
        }
        return res > 0 ? 1 : 0;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}

