//package com.coursera;
//
//import java.util.Comparator;
//import java.util.PriorityQueue;
//import java.util.Queue;
//import java.util.Scanner;
//
//public class ConnectingPoints {
//    private static double minimumDistance(int[] x, int[] y) {
//        double result = 0.;
//        Queue<Edge> queue = new PriorityQueue<Edge>(new Comparator<Edge>() {
//            @Override
//            public int compare(Edge o1, Edge o2) {
//                return (int)(Math.round(o1.distance - o2.distance));
//            }
//        });
//        for (int i = 0; i < x.length; i++) {
//            for (int j = i + 1; j < x.length; j++) {
//                double d = Math.sqrt(Math.pow(x[i] - x[j], 2) +  Math.pow(y[i] - y[j], 2));
//                queue.add(new Edge(i, j, d));
//            }
//        }
//        int [] parents = new int[x.length];
//        for (int i = 0; i < parents.length; i++) {
//            parents[i] = i;
//        }
//        while (!queue.isEmpty()) {
//            Edge e = queue.poll();
//            int f = findParent(parents, e.in);
//            int s = findParent(parents, e.out);
//            if (f != s) {
//                parents[f] = parents[s];
//                result += e.distance;
//            }
//        }
//        return result;
//    }
//
//    static int findParent(int[] parents, int i) {
//        if (i == parents[i]) {
//            return i;
//        }
//        return parents[i] = findParent(parents, parents[i]);
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int[] x = new int[n];
//        int[] y = new int[n];
//        for (int i = 0; i < n; i++) {
//            x[i] = scanner.nextInt();
//            y[i] = scanner.nextInt();
//        }
//        System.out.println(minimumDistance(x, y));
//    }
//}
//
//class Edge {
//    int in;
//    int out;
//    double distance;
//
//    Edge( int in, int out, double distance) {
//        this.in = in;
//        this.out = out;
//        this.distance = distance;
//    }
//}