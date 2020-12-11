package com.MIPT;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Edge implements Comparable<Edge> {
    int weight;
    int keyStart;
    int keyEnd;

    Edge(int weight, int keyStart, int keyEnd) {
        this.weight = weight;
        this.keyStart = keyStart;
        this.keyEnd = keyEnd;
    }

    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}

class MinSpanningTree {

    private ArrayList<Edge> e;

    MinSpanningTree() {
        // ребра минимального остовного дерева, изначально пуст
        this.e = new ArrayList<Edge>();
    }

    ArrayList<Edge> mst(ArrayList<Edge> edges, int[] parents) {
        // для каждого ребра определяем, получится ли цикл при добавлении его в минимальное оставное дерево
        for (Edge edge : edges) {
            if (unit(parents, edge.keyStart, edge.keyEnd)) {
                // если цикла нет, добавляем ребро
                parents[edge.keyEnd] = edge.keyStart;
                this.e.add(edge);
            }
        }
        return this.e;
    }

    // поиск родителя вершины
    private int find(int i, int[] parent) {
        if (i == parent[i]) {
            return i;
        }
        return parent[i] = find(parent[i], parent);
    }

    private boolean unit(int[] parent, int start, int end) {
        return !(find(start, parent) == find(end, parent));
    }
}

public class Kruskal {
    private final InputStream input;
    private final OutputStream output;

    public static void main(final String... args) {
        new Kruskal(System.in, System.out).run();
    }

    public Kruskal(final InputStream in, final OutputStream out) {
        this.input = in;
        this.output = out;
    }

    public void run() {
        Scanner scanner = new Scanner(this.input);
        PrintStream out = new PrintStream(this.output);
        out.println("введите количество ребер: ");
        int n = scanner.nextInt();
        ArrayList<Edge> edges = new ArrayList<Edge>();
        out.println("введите количество вершин: ");
        int m = scanner.nextInt();
        // массив родителей для каждой вершины
        int[] parents = new int[m];
        // изначально каждая вершина ни с кем не связана и сама себе родитель
        for (int i = 0; i < m; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < n; i++) {
            out.println("введите вес ребра: ");
            int w = scanner.nextInt();
            out.println("введите начало - номер вершины: ");
            int s = scanner.nextInt();
            out.println("введите конец - номер вершины: ");
            int e = scanner.nextInt();
            edges.add(new Edge(w, s, e));
        }
        // отсортированный по весу ребра массив ребер
        Collections.sort(edges);
        ArrayList<Edge> e = new MinSpanningTree().mst(edges, parents);
        // конец, печатаем полученные значения
        for (Edge edge : e) {
            out.println("Start: " + edge.keyStart + " Weight: " + edge.weight + " End: " + edge.keyEnd);
        }
    }
}

