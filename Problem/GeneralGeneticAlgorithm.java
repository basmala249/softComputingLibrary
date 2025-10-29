package Problem;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import Chromosomes.*;
import CrossOverStrategy.*;
import FitnessFunctions.*;
import MutationStratgey.*;
import ReplacementStratgey.*;
import SelectionStratgey.*;


public class GeneralGeneticAlgorithm<T> extends GeneticAlgorithmMethod {

    private final Random rand = new Random();
    private final List<Chromosome<T>> population = new ArrayList<>();

    private IFitnessFunction<T> fitnessFunction;
    private SelectionInterface<T> selectionStrategy;
    private ICrossOver<T> crossoverStrategy;
    private IMutation<T> mutationStrategy;
    private IReplacement<T> replacementStrategy;
    private int numberToBeSelected;

    public GeneralGeneticAlgorithm(GeneticAlgorithmParameters geneticParams) {
        super(geneticParams);
    }

    @Override
    Chromosome<T> run() {
        throw new UnsupportedOperationException("Use runGeneticAlgorithm() instead.");
    }

    /**
     * Run a fully generic genetic algorithm.
     */
    public Chromosome<T> runGeneticAlgorithm(
            IFitnessFunction<T> fitnessFunction,
            SelectionInterface<T> selectionStrategy,
            ICrossOver<T> crossoverStrategy,
            IMutation<T> mutationStrategy,
            IReplacement<T> replacementStrategy,
            Chromosome<T> initialChromosome,
            Predicate<Chromosome<T>> stopCondition, 
            Boolean isMinimization,
            int numberToBeSelected
    ) 
    {
        // --- Assign Strategies ---
        this.fitnessFunction = fitnessFunction;
        this.selectionStrategy = selectionStrategy;
        this.crossoverStrategy = crossoverStrategy;
        this.mutationStrategy = mutationStrategy;
        this.replacementStrategy = replacementStrategy;
        this.numberToBeSelected = numberToBeSelected;

        // --- Initialize Population ---
        for (int i = 0; i < geneticParams.getPopulationSize(); i++) {
            Chromosome<T> newChromosome = initialChromosome.copy();
            newChromosome.InitializeChromosome();
            population.add(newChromosome);
        }

        int generation = 0;

        // --- Main Loop ---
        while (generation < geneticParams.getGenerations()) {

            // Check stop condition
            Optional<Chromosome<T>> maybeSolution = population.stream()
                    .filter(stopCondition)
                    .findFirst();

            if (maybeSolution.isPresent()) {
                Chromosome<T> solution = maybeSolution.get();
                System.out.println("Solution found at generation " + generation);
                solution.PrintChromosome(); 
                return solution;
            }

            double variance = calculateFitnessVariance(population);
            if (variance <= 0.0) {
                System.out.println("at generation: " + generation + " variance = 0, stop for best performance.");
                break;
            }

            // --- Selection ---
            List<Chromosome<T>> selectedParents =
                    selectionStrategy.select(population, numberToBeSelected, isMinimization);

            // --- Crossover ---
            List<Chromosome<T>> newOffsprings = applyCrossover(selectedParents, isMinimization);
            
            // --- Mutation ---
            for (int i = 0; i < newOffsprings.size(); i++) {
                if (rand.nextDouble() < geneticParams.getMutationRate()) {
                        newOffsprings.set(i, mutationStrategy.mutate(newOffsprings.get(i)));
                    }
            }
            
            // --- Replacement ---
            List<Chromosome<T>> newPopulation =
                    replacementStrategy.replace(population, newOffsprings, fitnessFunction, isMinimization);

            population.clear();
            population.addAll(newPopulation);

            generation++;
        }

        System.out.println("No solution found after " + generation + " generations.");
        return printBestSolution(isMinimization);
    }


    // ---------------------------- 

    private List<Chromosome<T>> applyCrossover(List<Chromosome<T>> selectedChromosomes, boolean isMinimization) {
        int count = 0;
        List<Chromosome<T>> newOffsprings = new ArrayList<>();
        for(int i = 0;i < selectedChromosomes.size() ;i += 2) {
                double randomNum =  rand.nextDouble();
                if(randomNum < geneticParams.getCrossoverRate()) {
                    
                    Chromosome<T> firstParent = selectedChromosomes.get(i);
                    Chromosome<T> secondParent = selectedChromosomes.get((i + 1) % selectedChromosomes.size());

                    if(i == 0 || (i + 1) % selectedChromosomes.size() == 0) count++;
                   
                    // apply crossover
                    List<Chromosome<T>> offSprings = crossoverStrategy.crossOver(firstParent, secondParent);
                    newOffsprings.add(offSprings.get(0));
                    newOffsprings.add(offSprings.get(1));
                   
                }
               
        }
        // Odd Size Choose Best
        if(selectedChromosomes.size() % 2 != 0 && count == 2) {
            Chromosome<T> last = null;
           
            double fit1 = fitnessFunction.evaluate(newOffsprings.get(newOffsprings.size() - 1));
            double fit2 = fitnessFunction.evaluate(newOffsprings.get(0));
            
            if(isMinimization) {
                // Bec Here we are minimization problem
                last = (fit1 <= fit2) ? newOffsprings.get(newOffsprings.size() - 1) : newOffsprings.get(0);
            } else {
                // Maximization Problem
                last = (fit1 >= fit2) ? newOffsprings.get(newOffsprings.size() - 1) : newOffsprings.get(0);
            }
           
            newOffsprings.set(0, last);
            newOffsprings.remove(newOffsprings.size() - 1);
        }


        return newOffsprings;

        
        
    }


    private Chromosome<T> printBestSolution(boolean isMinimization) {
        Comparator<Chromosome<T>> comparator =
                Comparator.comparingDouble(fitnessFunction::evaluate);

        // If we are maximizing fitness, reverse the comparator
        if (!isMinimization) {
            comparator = comparator.reversed();
        }

        Chromosome<T> best = population.stream()
                .min(comparator)
                .orElse(null);

        if (best != null) {
            System.out.println("Best solution found:");
            best.PrintChromosome();
        }
        return best;
    }

    // Calculate population fitness variance to monitor diversity
    private double calculateFitnessVariance(List<Chromosome<T>> population) {
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


}
