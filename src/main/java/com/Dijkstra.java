
package com;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Dijkstra {
    private final InputStream input;
    private final OutputStream output;

    public static void main(final String... args) {
        new Dijkstra(System.in, System.out).run();
    }

    public Dijkstra(final InputStream in, final OutputStream out) {
        this.input = in;
        this.output = out;
    }

    public void run() {
        Scanner scanner = new Scanner(this.input);
        PrintStream out = new PrintStream(this.output);
        out.println("введите количество вершин: ");
        int n = scanner.nextInt();
        Graph graph = new Graph(n);
        out.println("введите количество ребер: ");
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            out.println("введите вес ребра: ");
            int w = scanner.nextInt();
            if (w < 0) {
                out.println("отрицательный вес не разрешен!");
                return;
            }
            out.println("введите начало - номер вершины: ");
            int s = scanner.nextInt();
            out.println("введите конец - номер вершины: ");
            int e = scanner.nextInt();
            graph.matrix[s][e] = w;
            graph.matrix[e][s] = w;
        }
        out.println("введите номер вершины, до которой считаем расстояние: ");
        int v = scanner.nextInt();
        // массив расстояний от выбранной вершины до остальных
        int[] distances = graph.dijkstraDistance(v, n);
        out.println("полученные расстояния: ");
        for (int i = 0; i < n; i++) {
            out.print(distances[i] + " ");
        }
    }
}

class Graph {
    private static final int infinity = 2147483647;
    int[][] matrix;  // матрица смежностей

    Graph(int n) {
        this.matrix = new int[n][n];
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                this.matrix[i][j] = infinity;
            }
        }
    }

    private int getMin(boolean[] isVisited, int[] distances, int n) {
        int min = infinity;
        int v = -1;
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                if (min > distances[i]) {
                    min = distances[i];
                    v = i;
                }
            }
        }
        return v;
    }

    int[] dijkstraDistance(int v, int n) {
        int[] distances = new int[n];
        // массив, показывающий, была ли посещена вершина c номером i
        boolean[] isVisited = new boolean[n];
        // изначально все расстояния до выбранной вершины infinity, и все вершины не посещены
        distances[v] = 0;
        for (int i = 0; i < n; i++) {
            if (i != v) {
                distances[i] = infinity;
            }
        }
        for (; ; ) {
            // находим наименее удаленную вершину
            int q = getMin(isVisited, distances, n);
            // если такой нет (все вершины посещены), выходим
            if (q == -1) {
                break;
            }
            isVisited[q] = true;
            // для каждой непосещенной вершины, у которой есть ребро с найденной, улучшаем расстрояние (релаксация)
            for (int j = 0; j < n; j++) {
                if (!isVisited[j] && this.matrix[q][j] < infinity) {
                    distances[j] = Math.min(distances[j], distances[q] + this.matrix[q][j]);
                }
            }
        }
        return distances;
    }
}