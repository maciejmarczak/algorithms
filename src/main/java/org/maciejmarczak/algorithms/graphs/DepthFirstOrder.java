package org.maciejmarczak.algorithms.graphs;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class DepthFirstOrder {

    private final Digraph G;
    private final boolean[] visited;

    private final Queue<Integer> pre;
    private final Queue<Integer> post;
    private final Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G) {
        this.G = G;
        this.visited = new boolean[G.V()];

        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new Stack<>();

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int s) {
        pre.add(s);
        visited[s] = true;

        for (int v : G.adj(s)) {
            if (!visited[v]) {
                dfs(v);
            }
        }

        post.add(s);
        reversePost.push(s);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }

    private static <T> void printIterable(String label, Iterable<T> iterable) {
        System.out.println("\n" + label + ":");
        for (T it : iterable) {
            System.out.print(it + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("data/DFO.txt"));

        Digraph G = new Digraph(sc);
        DepthFirstOrder dfo = new DepthFirstOrder(G);

        printIterable("Pre", dfo.pre());
        printIterable("Post", dfo.post());
        printIterable("ReversePost", dfo.reversePost());
    }

}
