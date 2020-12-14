package com.coursera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
        int[] colors = new int[adj.length];
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] != 2) {
                cycle(i, adj, colors);
            }
        }
        if (Arrays.stream(colors).allMatch(a -> a == 2)) {
            return 0;
        }
        return 1;
    }

    static void cycle(int v, ArrayList<Integer>[] adj, int[] colors) {
        colors[v] = 1;
        for (int u: adj[v]) {
            if (colors[u] != 1) {
                cycle(u, adj, colors);
            }
            else {
                return;
            }
        }
        colors[v] = 2;
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
        }
        System.out.println(acyclic(adj));
    }
}

