package Problem;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;
import Chromosomes.*;
import CrossOverStrategy.*;
import FitnessFunctions.*;
import MutationStratgey.*;
import ReplacementStratgey.*;
import SelectionStratgey.*;
import Utils.PrintGrid;

// implementation of N-Queens problem using Genetic Algorithm
public class NQueensGeneticAlgorithmImplement extends GeneticAlgorithmMethod {
    
    public NQueensGeneticAlgorithmImplement(GeneticAlgorithmParameters geneticParams) {
        super(geneticParams);
    }

    // Calculate population fitness variance to monitor diversity
    private double calculateFitnessVariance(List<Chromosome<Integer>> population, IFitnessFunction<Integer> fitnessFunction) {
        List<Double> fitnessValues = population.stream()
            .map(ch -> fitnessFunction.evaluate(ch))
            .collect(Collectors.toList());
        double mean = fitnessValues.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double variance = fitnessValues.stream()
            .mapToDouble(f -> Math.pow(f - mean, 2))
            .average()
            .orElse(0.0);
        return variance;
    }

    @Override
    void run() {
        int currentGeneration = 0;
        List<Chromosome<Integer>> population = new ArrayList<>();
        IFitnessFunction<Integer> fitnessFunction = new N_QueensCaseStudyFitnessFunction(8);

        // Initialize population
        for(int i = 0;i < geneticParams.getPopulationSize();i++) {
            Chromosome<Integer> newChromosome = new PermutationChromosome(geneticParams.getChromosomeLength(), fitnessFunction);
            population.add(newChromosome);
        }
      
        SelectionInterface<Integer> selectionStrategy = new RankSelection<Integer>();
        ICrossOver<Integer> crossoverStrategy = new OrderOneCrossOver<Integer>();
        IMutation<Integer> mutationStrategy = new InsertMutationStrategy<Integer>();
        IReplacement<Integer> replacementStrategy = new ElitismReplacement<Integer>(2);
        int numberToBeSelected = 2;

        while(currentGeneration < geneticParams.getGenerations()){

            // Evaluate population and check for solution
            for (Chromosome<Integer> chromosome : population) {
                System.out.println("Debug>> Evaluating Chromosome: ");
                chromosome.PrintChromosome();
                double fitness = fitnessFunction.evaluate(chromosome);
                System.out.print(", Fitness: " + fitness);
                if (fitness == 0) { // Solution found (no conflicts in N-Queens)
                    System.out.println("Solution Found at Generation " + currentGeneration);
                    chromosome.PrintChromosome();
                    PrintGrid.printNQueensGrid(chromosome);
                    return;
                }
            }

            // Select parents & generate offspring via crossover

            List<Chromosome<Integer>> selectedChromosomes = selectionStrategy.select(population, numberToBeSelected, false);
            List<Chromosome<Integer>> newOffsprings = new ArrayList<>();
        
            for(int i = 0;i < selectedChromosomes.size();i += 2) {
                double randomNum = getRandomNumber();
                System.out.println("Debug>>  " + randomNum + " < " + geneticParams.getCrossoverRate());
                if(randomNum < geneticParams.getCrossoverRate()) {
                    List<Chromosome<Integer>> parents = new ArrayList<>();
                    parents.add(selectedChromosomes.get(i));
                    parents.add(selectedChromosomes.get(i + 1));
                    System.out.print("Debug>> Chromosome1:: ");
                    selectedChromosomes.get(i).PrintChromosome();
                    System.out.print("Debug>> Chromosome2:: ");
                    selectedChromosomes.get(i + 1).PrintChromosome();
                   
                    // apply crossover
                    List<Chromosome<Integer>> offSprings = crossoverStrategy.crossOver(parents, true);
                    newOffsprings.add(offSprings.get(0));
                    newOffsprings.add(offSprings.get(1));

                    System.out.println("Debug>> Crossover Applied ");
                    System.out.print("Debug>> Offspring1:: ");
                    offSprings.get(0).PrintChromosome();
                    System.out.print("Debug>> Offspring2:: ");
                    offSprings.get(1).PrintChromosome();
                   
                }
               
            }

            // Adjust mutation rate based on diversity
            double dynamicMutationRate = geneticParams.getMutationRate();
            double variance = calculateFitnessVariance(population, fitnessFunction);
            if (variance < 1.0) { // Threshold for low diversity (adjust as needed)
                dynamicMutationRate = Math.min(dynamicMutationRate * 2, 0.5); // Double mutation rate, cap at 0.5
                System.out.println("Debug>> Low diversity detected, increasing mutation rate to: " + dynamicMutationRate);
            }
            
            // Apply mutation to offspring
            for(int i = 0;i < newOffsprings.size();i++) {
                double randomNum = getRandomNumber();
                if(randomNum < dynamicMutationRate) {
                    newOffsprings.set(i, mutationStrategy.mutate(newOffsprings.get(i)));    
                }

            }


            // Perform replacement 
            currentGeneration++;
            population = replacementStrategy.replace(population, newOffsprings, fitnessFunction, true);
            
           
        }
            
        
    }
    
}