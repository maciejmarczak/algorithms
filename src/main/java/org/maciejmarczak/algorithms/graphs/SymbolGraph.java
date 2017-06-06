package org.maciejmarczak.algorithms.graphs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SymbolGraph {

    private Map<String, Integer> st;
    private String[] keys;
    private Graph G;

    public SymbolGraph(String filename, String delim)
            throws IOException {

        Scanner sc;
        String[] vertices;
        st = new HashMap<>();

        sc = new Scanner(new File(filename));
        while (sc.hasNextLine()) {
            vertices = sc.nextLine().split(delim);

            for (String vertex : vertices) {
                if (!st.containsKey(vertex)) {
                    st.put(vertex, st.size());
                }
            }
        }

        keys = new String[st.size()];
        for (String name : st.keySet()) {
            keys[st.get(name)] = name;
        }

        G = new Graph(st.size());

        sc = new Scanner(new File(filename));
        while (sc.hasNextLine()) {
            vertices = sc.nextLine().split(delim);
            int s = st.get(vertices[0]);

            for (int i = 1; i < vertices.length; i++) {
                G.addEdge(s, st.get(vertices[i]));
            }
        }
    }

    public boolean contains(String key) {
        return st.containsKey(key);
    }

    public int index(String key) {
        return st.get(key);
    }

    public String name(int index) {
        return keys[index];
    }

    public Graph G() {
        return G;
    }

    public static void main(String[] args) throws IOException {
        String filename = args[0];
        String delim = args[1];

        SymbolGraph sg = new SymbolGraph(filename, delim);
        Graph G = sg.G();

        Scanner sc = new Scanner(System.in);

        String source = sc.nextLine();
        while (!"q".equals(source)) {
            for (int w : G.adj(sg.index(source))) {
                System.out.println("\t" + sg.name(w));
            }
            source = sc.nextLine();
        }
    }
}
