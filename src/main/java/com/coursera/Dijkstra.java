package com.coursera;

import java.util.*;

public class Dijkstra {
    private static long distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int[] dist = new int[adj.length];
        boolean[] visited = new boolean[adj.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        PriorityQueue<Integer> vertexes = new PriorityQueue<>((a, b) -> dist[a] - dist[b]);
        vertexes.add(s);
        while (!vertexes.isEmpty()) {
            int q = vertexes.poll();
            visited[q] = true;
            for (int i = 0; i < adj[q].size(); i++) {
                if (!visited[adj[q].get(i)] && dist[adj[q].get(i)] > dist[q] + cost[q].get(i)) {
                    dist[adj[q].get(i)] = dist[q] + cost[q].get(i);
                    vertexes.add(adj[q].get(i));
                }
            }
        }
        return dist[t] == Integer.MAX_VALUE ? -1 : dist[t];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

