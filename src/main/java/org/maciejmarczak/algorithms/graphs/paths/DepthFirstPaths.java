package org.maciejmarczak.algorithms.graphs.paths;

import org.maciejmarczak.algorithms.graphs.Graph;

class DepthFirstPaths extends Paths {

    DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        edgeTo[s] = -1;
        dfs(G, s);
    }

    private void dfs(Graph G, int s) {
        marked[s] = true;
        for (int v : G.adj(s)) {
            if (!marked[v]) {
                edgeTo[v] = s;
                dfs(G, v);
            }
        }
    }
}
