package GeneticAlgorithm.Utils;

import GeneticAlgorithm.Chromosomes.Chromosome;

public class PrintGrid {

    public static void printNQueensGrid(Chromosome<Integer> chromosome) {
        int n = chromosome.getSize();
        String queen = "Q"; // Queen symbol
        String empty = "·"; // Empty cell symbol

        // === Print column numbers (1-based) ===
        System.out.print("    ");  
        for (int col = 1; col <= n; col++) {
            System.out.printf("  %2d  ", col);
        }
        System.out.println();

        // === Print top border ===
        System.out.print("    ");
        for (int i = 0; i < n; i++) System.out.print("------");
        System.out.println("-");

        // === Print each row ===
        for (int row = 0; row < n; row++) {
            System.out.printf("%2d |", row + 1); 
            for (int col = 0; col < n; col++) {
                //System.out.println(row + " " + board.get(row) + " " + col);
                if (chromosome.getIndex(col) - 1 == row )
                    System.out.printf("  %s  |", queen); // Queen in her column
                else
                    System.out.printf("  %s  |", empty); // Empty cell
            }
            System.out.println();

            // Divider line
            System.out.print("    ");
            for (int i = 0; i < n; i++) System.out.print("------");
            System.out.println("-");
        }
    }
}