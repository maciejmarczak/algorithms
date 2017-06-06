package org.maciejmarczak.algorithms.graphs.paths;

import java.util.Stack;

abstract class Paths {

    boolean[] marked;
    int[] edgeTo;
    int s;

    boolean hasPathTo(int v) {
        return marked[v];
    }

    Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> stack = new Stack<>();

        for (int x = v; x != this.s; x = edgeTo[x]) {
            stack.push(x);
        }
        stack.push(this.s);

        return stack;
    }
}
