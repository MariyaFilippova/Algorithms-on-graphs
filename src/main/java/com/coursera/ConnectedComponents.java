package com.coursera;

import java.util.*;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int[] groups = new int[adj.length];
        boolean[] visited = new boolean[adj.length];
        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) {
                dfs(adj, visited, i, groups, i);
            }
        }
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < adj.length; i++) {
            hashSet.add(groups[i]);
        }
        return hashSet.size();
    }

    static void dfs(ArrayList<Integer>[] adj, boolean[] visited, int v, int[] groups, int i) {
        if (visited[v]) {
            return;
        }
        visited[v] = true;
        groups[v] = i;
        for (int u : adj[v]) {
            dfs(adj, visited, u, groups, i);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
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
        System.out.println(numberOfComponents(adj));
    }
}

