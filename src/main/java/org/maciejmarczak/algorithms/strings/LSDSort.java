package org.maciejmarczak.algorithms.strings;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LSDSort {

    private static void sort(List<String> list, int w) {
        int N = list.size();
        int R = 256;
        String[] aux = new String[N];

        for (int i = w - 1; i >= 0; i--) {
            int[] count = new int[R + 1];

            // compute frequency
            for (String str : list) {
                count[str.charAt(i) + 1]++;
            }

            // transform to indices
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            // distribute
            for (int n = 0; n < N; n++) {
                aux[count[list.get(n).charAt(i)]++] = list.get(n);
            }

            // copy
            for (int j = 0; j < N; j++) {
                list.set(j, aux[j]);
            }
        }
    }

    private static void printPlates(List<String> plates) {
        plates.forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("data/plates.txt"));

        List<String> plates = new LinkedList<>();
        while (sc.hasNextLine()) {
            plates.add(sc.nextLine());
        }

        sort(plates, plates.get(0).length());
        printPlates(plates);
    }

}
