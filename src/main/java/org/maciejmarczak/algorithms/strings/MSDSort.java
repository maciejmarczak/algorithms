package org.maciejmarczak.algorithms.strings;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MSDSort {

    private static String[] aux;
    private static int R = 256;
    private static int[] count;

    private static int charAt(String s, int d) {
        return s.length() > d ? s.charAt(d) : -1;
    }

    private static void sort(List<String> list) {
        aux = new String[list.size()];
        sort(list, 0, list.size() - 1, 0);
    }

    private static void sort(List<String> list, int lo, int hi, int d) {
        if (hi <= lo + 5) {
            Collections.sort(list.subList(lo, hi + 1));
            return;
        }

        count = new int[R + 2];

        // count frequencies
        for (int i = lo; i <= hi; i++) {
            count[charAt(list.get(i), d) + 2]++;
        }

        // transform to indices
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }

        // distribute
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(list.get(i), d) + 1]++] = list.get(i);
        }

        // copy back
        for (int i = lo; i <= hi; i++) {
            list.set(i, aux[i - lo]);
        }

        for (int r = 0; r < R; r++) {
            sort(list, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    private static void printLines(List<String> lines) {
        lines.forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("data/msd.txt"));

        List<String> lines = new LinkedList<>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        sort(lines);
        printLines(lines);
    }
}
