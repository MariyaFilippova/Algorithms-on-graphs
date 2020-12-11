package com.MIPT;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

class RBTree {
    RBNode root;

    private void insert(int key) {
        root = insertRecursive(root, key, null);
    }

    private void delete(int key) {
        root = deleteRecursive(root, key);
    }

    private void leftRotate(RBNode node) {
        RBNode y = node.right;
        node.right = y.left;
        if (y.left != null) {
            y.left.parent = node;
        }
        y.parent = node.parent;
        if (node.parent == null) {
            this.root = y;
        } else if (node == node.parent.left) {
            node.parent.left = y;
        } else if (node == node.parent.right) {
            node.parent.right = y;
        }
        y.left = node;
        node.parent = y;
    }

    private void rightRotate(RBNode node) {
        RBNode z = node.left;
        node.left = z.right;
        if (z.right != null) {
            z.right.parent = node;
        }
        z.parent = node.parent;
        if (node.parent == null) {
            this.root = z;
        } else if (node == node.parent.right) {
            node.parent.right = z;
        } else if (node == node.parent.left) {
            node.parent.left = z;
        }
        z.right = node;
        node.parent = z;
    }

    void RBInsertion(int key) {
        insert(key);
        RBNode node = findNode(key);
        while (node != root && node.parent.color.equals("red")) {
            if (node.parent == node.parent.parent.left) {
                RBNode y = node.parent.parent.right;
                if (y != null) {
                    if (y.color.equals("red")) {
                        node.parent.color = "black";
                        y.color = "black";
                        node.parent.parent.color = "red";
                        node = node.parent.parent;
                    }
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.color = "black";
                    node.parent.parent.color = "red";
                    rightRotate(node.parent.parent);
                }
            } else {
                RBNode y = node.parent.parent.left;
                if (y != null) {
                    if (y.color.equals("red")) {
                        node.parent.color = "black";
                        y.color = "black";
                        node.parent.parent.color = "red";
                        node = node.parent.parent;
                    }
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.color = "black";
                    node.parent.parent.color = "red";
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.color = "black";
    }

    private RBNode findNode(int key) {
        RBNode node = root;
        while (node.key != key) {
            if (node.key < key) {
                node = node.right;
            }
            if (node.key > key) {
                node = node.left;
            }
        }
        return node;
    }

    private RBNode findPrevNode(RBNode node) {
        int smallestValue = findSmallestKey(node.right);
        node.key = smallestValue;
        node.right = deleteRecursive(node.right, smallestValue);
        return node;
    }

    private int findSmallestKey(RBNode node) {
        return node.left == null ? node.key : findSmallestKey(node.left);
    }

    private RBNode deleteRecursive(RBNode node, int key) {
        if (node == null) {
            return null;
        }
        if (key == node.key) {
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.right == null) {
                node.left.parent = node.parent;
                return node.left;
            }
            if (node.left == null) {
                node.right.parent = node.parent;
                return node.right;
            }
            RBNode prev = findPrevNode(node);
            node.left.parent = prev;
            node.right.parent = prev;
            return prev;
        }

        if (node.key < key) {
            node.right = deleteRecursive(node.right, key);
        } else {
            node.left = deleteRecursive(node.left, key);
        }
        return node;
    }

    private RBNode insertRecursive(RBNode node, int key, RBNode parent) {
        if (node == null) {
            return new RBNode(key, parent, "red");
        }
        if (node.key < key) {
            node.right = insertRecursive(node.right, key, node);
        } else if (node.key > key) {
            node.left = insertRecursive(node.left, key, node);
        }
        return node;
    }

    void printTree(RBNode node, PrintStream out) {
        if (node != null) {
            printTree(node.left, out);
            out.println(node.key + " " + node.color);
            printTree(node.right, out);
        }
    }
}

class RBNode {
    String color;
    int key;
    RBNode right;
    RBNode left;
    RBNode parent;

    RBNode(int key, RBNode parent, String color) {
        this.key = key;
        this.parent = parent;
        this.color = color;
    }
}

public class RBTreeBuilder {
    private final InputStream input;
    private final OutputStream output;

    public static void main(final String... args) {
        new RBTreeBuilder(System.in, System.out).run();
    }

    public RBTreeBuilder(final InputStream in, final OutputStream out) {
        this.input = in;
        this.output = out;
    }
    public void run() {
        Scanner scanner = new Scanner(this.input);
        PrintStream out = new PrintStream(this.output);
        RBTree tree = new RBTree();
        out.println("введите количество узлов в дереве: ");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            out.println("введите число: ");
            tree.RBInsertion(scanner.nextInt());
        }
        tree.printTree(tree.root, out);
        out.println("Корень: " + tree.root.key);
    }
}