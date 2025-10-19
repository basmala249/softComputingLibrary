package Utils;

import Chromosomes.Chromosome;

public class PrintGrid {

    public static void printNQueensGrid(Chromosome<Integer> chromosome) {
        int n = chromosome.getSize();
        String queen = "♛"; 
        // Print the grid
        System.out.println("\nN-Queens Solution Grid (" + n + "x" + n + "):");
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (chromosome.getIndex(col) == row) {
                    System.out.print(queen + " ");
                } 
                else {
                    System.out.print(". ");
                }
            }
            System.out.println(); 
        }
        System.out.println();
    }
}