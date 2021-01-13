package com.coursera;

import java.util.*;

public class ShortestPaths {

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {
        List<Integer> reach = new ArrayList<>();
        dfs(adj, s, reachable, reach);
        distance[s] = 0;
        for (int i = 0; i < reach.size() + 1; i++) {
            for (int j: reach) {
                for (int k = 0; k < adj[j].size(); k++) {
                    if (shortest[j] != 0 && distance[j] != Long.MAX_VALUE && distance[j] != Long.MIN_VALUE  && distance[adj[j].get(k)] > distance[j] + cost[j].get(k)) {
                        if (i == reach.size() -1) {
                            findNegativeCycle(shortest, adj[j].get(k), adj);
                            continue;
                        }
                        distance[adj[j].get(k)] = distance[j] + cost[j].get(k);
                    }
                }
            }
        }
    }

    static void findNegativeCycle(int[] shortest, int j, ArrayList<Integer>[] adj) {
        if (shortest[j] == 0) {
            return;
        }
        shortest[j] = 0;
        for (int i = 0; i < adj[j].size(); i++) {
            findNegativeCycle(shortest, adj[j].get(i), adj);
        }
    }

    static void dfs(ArrayList<Integer>[] adj, int s, int[] visited, List<Integer> reach) {
        if (visited[s] == 1) {
            return;
        }
        visited[s] = 1;
        reach.add(s);
        for (int u: adj[s]) {
            dfs(adj, u, visited, reach);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (distance[i] < 0 || shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

