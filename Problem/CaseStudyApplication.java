package Problem;
import java.util.Scanner;

import Chromosomes.*;
import CrossOverStrategy.*;
import FitnessFunctions.*;
import MutationStratgey.*;
import ReplacementStratgey.*;
import SelectionStratgey.*;

public class CaseStudyApplication {

        // Default Parameters
        public static int populationSize = 6;
        public static int generations = 1000;
        public static int chromosomeLength = 8;
        public static double crossoverRate = 0.5;
        public static double mutationRate = 0.02;

    public static void main(String[] args) {
        // Read parameters from user
        readParametersFromUser();
         
        // Initialize Genetic Algorithm with N-Queens Case Study
        GeneticAlgorithmParameters params =
            new GeneticAlgorithmParameters(populationSize, generations, chromosomeLength, crossoverRate, mutationRate);

        
        // GeneticAlgorithmMethod ga_engine = new NQueensGeneticAlgorithmImplement(params);
        // Chromosome<Integer> solution =ga_engine.run();

        GeneralGeneticAlgorithm<Integer> ga = new GeneralGeneticAlgorithm<>(params);
        Chromosome<Integer> solution = ga.runGeneticAlgorithm(
            new N_QueensCaseStudyFitnessFunction(params.getChromosomeLength()),
            new RankSelection<>(),
            new OrderOneCrossOver<>(),
            new InsertMutationStrategy<>(),
            new ElitismReplacement<>(2),
            new PermutationChromosome(params.getChromosomeLength(), new N_QueensCaseStudyFitnessFunction(params.getChromosomeLength())),
            c -> new N_QueensCaseStudyFitnessFunction(params.getChromosomeLength()).evaluate(c) == 0,
            true 
        );

        System.out.println("Solution Returned:");
        solution.PrintChromosome();

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