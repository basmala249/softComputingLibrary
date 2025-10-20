package Problem;
import java.util.List;
import java.util.Scanner;

import Chromosomes.BinaryChromosome;
import Chromosomes.Chromosome;
import CrossOverStrategy.NPointCrossOver;

public class CaseStudyApplication {

        // Default Parameters
        public static int populationSize = 6;
        public static int generations = 10;
        public static int chromosomeLength = 10;
        public static double crossoverRate = 0.5;
        public static double mutationRate = 0.02;

    public static void main(String[] args) {
        // Read parameters from user
        readParametersFromUser();
         
        // Initialize Genetic Algorithm with N-Queens Case Study
        GeneticAlgorithmParameters params =
            new GeneticAlgorithmParameters(populationSize, generations, chromosomeLength, crossoverRate, mutationRate);
        GeneticAlgorithmMethod ga_engine = new NQueensGeneticAlgorithmImplement(params);
        ga_engine.run();

    


    }

    private static void readParametersFromUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Use default parameters? (y/n): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (!choice.equals("y")) {
                System.out.print("Enter Population Size: ");
                populationSize = scanner.nextInt();

                System.out.print("Enter Number of Generations: ");
                generations = scanner.nextInt();

                System.out.print("Enter Chromosome Length (N for N-Queens): ");
                chromosomeLength = scanner.nextInt();

                System.out.print("Enter Crossover Rate : ");
                crossoverRate = scanner.nextDouble();

                System.out.print("Enter Mutation Rate : ");
                mutationRate = scanner.nextDouble();
        }
        scanner.close();
    }
}