package com.coursera;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj, ArrayList<Integer>[] reversedAdj) {
        boolean[] visited = new boolean[adj.length];
        int components = 0;
        boolean[] component = new boolean[adj.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) {
                dfs(stack, i, visited, adj);
                while (!stack.isEmpty()) {
                    int u = stack.pop();
                    if (!component[u]) {
                        reversedDfs(u, component, reversedAdj, visited);
                        components++;
                    }
                }
            }
        }
        return components;
    }

    static void dfs(Stack<Integer> stack, int v, boolean[] visited, ArrayList<Integer>[] adj) {
        if (visited[v]) {
            return;
        }
        visited[v] = true;
        for ( int u: adj[v]) {
            dfs(stack, u, visited, adj);
        }
        stack.push(v);
    }

    static void reversedDfs(int v, boolean[] component, ArrayList<Integer>[] adj, boolean[] visited) {
        if (component[v]) {
            return;
        }
        component[v] = true;
        for (int u: adj[v]) {
            if (visited[u])
                reversedDfs(u, component, adj, visited);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] reversedAdj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            reversedAdj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            reversedAdj[y - 1].add(x - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj, reversedAdj));
    }
}

