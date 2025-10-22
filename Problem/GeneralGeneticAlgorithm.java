package Problem;

import java.util.*;
import java.util.function.Predicate;


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
            Boolean isMinimization
    ) 
    {
        // --- Assign Strategies ---
        this.fitnessFunction = fitnessFunction;
        this.selectionStrategy = selectionStrategy;
        this.crossoverStrategy = crossoverStrategy;
        this.mutationStrategy = mutationStrategy;
        this.replacementStrategy = replacementStrategy;

        // --- Initialize Population ---
        for (int i = 0; i < geneticParams.getPopulationSize(); i++) {
            Chromosome<T> newChromosome = initialChromosome.copy();
            newChromosome.InitializeChromosome();
            population.add(newChromosome);
        }

        int generation = 0;
        int numberToBeSelected = 2;

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

            // --- Selection ---
            List<Chromosome<T>> selectedParents =
                    selectionStrategy.select(population, numberToBeSelected, isMinimization);

            // --- Crossover ---
            List<Chromosome<T>> newOffsprings = applyCrossover(selectedParents);
            
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

        System.out.println("No solution found after " + geneticParams.getGenerations() + " generations.");
        return printBestSolution();
    }


    // ---------------------------- 

    private List<Chromosome<T>> applyCrossover(List<Chromosome<T>> parents) {
        List<Chromosome<T>> offsprings = new ArrayList<>();

        for (int i = 0; i < parents.size(); i += 2) {
            double randomNum = rand.nextDouble();
            if (randomNum < geneticParams.getCrossoverRate()) {
                Chromosome<T> p1 = parents.get(i);
                Chromosome<T> p2 = parents.get((i + 1) % parents.size());
                offsprings.addAll(crossoverStrategy.crossOver(p1, p2));
            }
        }
        return offsprings;
    }

    private Chromosome<T> printBestSolution() {
        Chromosome<T> best = population.stream()
                .min(Comparator.comparingDouble(fitnessFunction::evaluate))
                .orElse(null);

        if (best != null) {
            System.out.println("Best solution found:");
            best.PrintChromosome();
        }
        return best;
    }
}
