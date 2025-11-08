package Problem;
import java.util.Scanner;

import GeneticAlgorithm.Chromosomes.*;
import GeneticAlgorithm.CrossOverStrategy.*;
import GeneticAlgorithm.FitnessFunctions.*;
import GeneticAlgorithm.MutationStratgey.*;
import GeneticAlgorithm.ReplacementStratgey.*;
import GeneticAlgorithm.SelectionStratgey.*;

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

        // Initialize Genetic Algorithm with N-Queens Case Study -- using NQueensGeneticAlgorithm

        // GeneticAlgorithmParameters params =
        //     new GeneticAlgorithmParameters(populationSize, generations, chromosomeLength, crossoverRate, mutationRate);
        // GeneticAlgorithmMethod ga_engine = new NQueensGeneticAlgorithmImplement(params);
        // Chromosome<Integer> solution =ga_engine.run();

        //----------------------------------------------
        // Initialize Genetic Algorithm for Knapsack Problem -- using GeneralGeneticAlgorithm


        // chromosomeLength = 5;
        // GeneticAlgorithmParameters params =
        //     new GeneticAlgorithmParameters(populationSize, generations, chromosomeLength, crossoverRate, mutationRate);
        // // Define Knapsack problem data
        // int[] weights = {5, 4, 2, 2, 4};
        // int[] values  = {4, 4, 1, 7, 6};
        // int capacity = 10;

        // // Create Knapsack fitness function
        // KnapsackFitnessFunction fitnessFunction = new KnapsackFitnessFunction(weights, values, capacity);
        // // Run the algorithm
        // GeneralGeneticAlgorithm<Integer> ga = new GeneralGeneticAlgorithm<>(params);
        // Chromosome<Integer> solution = ga.runGeneticAlgorithm(
        //     fitnessFunction,
        //     new RankSelection<>(),
        //     new NPointCrossOver<>(3), 
        //     new InversionMutationStrategy<>(),
        //     new SteadyStateReplacement<>(),
        //     new BinaryChromosome(chromosomeLength, fitnessFunction),
        //     c -> fitnessFunction.evaluate(c) == fitnessFunction.getMaxPossibleValue(),
        //     false,
        //     2
        // );

        //----------------------------------------------
        // Initialize Genetic Algorithm for N-Queens Problem -- using GeneralGeneticAlgorithm

        GeneticAlgorithmParameters params =
             new GeneticAlgorithmParameters(populationSize, generations, chromosomeLength, crossoverRate, mutationRate);
        GeneralGeneticAlgorithm<Integer> ga = new GeneralGeneticAlgorithm<>(params);
        Chromosome<Integer> solution = ga.runGeneticAlgorithm(
            new N_QueensCaseStudyFitnessFunction(params.getChromosomeLength()),
            new RankSelection<>(),
            new OrderOneCrossOver<>(),
            new InsertMutationStrategy<>(),
            new ElitismReplacement<>(),
            new PermutationChromosome(params.getChromosomeLength(), new N_QueensCaseStudyFitnessFunction(params.getChromosomeLength())),
            c -> new N_QueensCaseStudyFitnessFunction(params.getChromosomeLength()).evaluate(c) == 0,
            true,
            2
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