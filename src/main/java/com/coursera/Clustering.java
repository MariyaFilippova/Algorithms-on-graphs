package com.coursera;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Clustering {
    private static double clustering(int[] x, int[] y, int k) {
        int components = x.length;
        Queue<com.coursera.Edge> queue = new PriorityQueue<Edge>(new Comparator<Edge>() {
            @Override
            public int compare(com.coursera.Edge o1, com.coursera.Edge o2) {
                return (int) (Math.round(o1.distance - o2.distance));
            }
        });
        for (int i = 0; i < x.length; i++) {
            for (int j = i + 1; j < x.length; j++) {
                double d = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
                queue.add(new com.coursera.Edge(i, j, d));
            }
        }
        int[] parents = new int[x.length];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        while (!queue.isEmpty()) {
            com.coursera.Edge e = queue.poll();
            int f = findParent(parents, e.in);
            int s = findParent(parents, e.out);
            if (components == k) {
                return e.distance;
            }
            if (f != s) {
                components--;
                parents[f] = parents[s];
            }
        }
        return -1.;
    }

    static int findParent(int[] parents, int i) {
        if (i == parents[i]) {
            return i;
        }
        return parents[i] = findParent(parents, parents[i]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}

class Edge {
    int in;
    int out;
    double distance;

    Edge(int in, int out, double distance) {
        this.in = in;
        this.out = out;
        this.distance = distance;
    }
}