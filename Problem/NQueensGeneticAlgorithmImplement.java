package Problem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import Chromosomes.*;
import CrossOverStrategy.*;
import FitnessFunctions.*;
import MutationStratgey.*;
import ReplacementStratgey.*;
import SelectionStratgey.*;
import Utils.*;

// implementation of N-Queens problem using Genetic Algorithm
public class NQueensGeneticAlgorithmImplement extends GeneticAlgorithmMethod {
    Random rand = new Random();
    List<Chromosome<Integer>> population = new ArrayList<>();
    IFitnessFunction<Integer> fitnessFunction = new N_QueensCaseStudyFitnessFunction(geneticParams.getChromosomeLength());
    SelectionInterface<Integer> selectionStrategy = new RankSelection<Integer>();
    ICrossOver<Integer> crossoverStrategy = new OrderOneCrossOver<Integer>();
    IMutation<Integer> mutationStrategy = new InsertMutationStrategy<Integer>();
    IReplacement<Integer> replacementStrategy = new ElitismReplacement<Integer>();
    
    public NQueensGeneticAlgorithmImplement(GeneticAlgorithmParameters geneticParams) {
        super(geneticParams);
    }


    @Override
    Chromosome<Integer> run() {
        int currentGeneration = 0;
        

        // Initialize population
        for(int i = 0;i < geneticParams.getPopulationSize();i++) {
            Chromosome<Integer> newChromosome = new PermutationChromosome(geneticParams.getChromosomeLength(), fitnessFunction);
            population.add(newChromosome);
        }
  
        int numberToBeSelected = 2;

        while(currentGeneration < geneticParams.getGenerations()){

            // Evaluate population and check for solution
            Chromosome<Integer> chromosome = didReachSolution();
            if(chromosome != null) {
                System.out.println("Solution Found at Generation " + currentGeneration);
                chromosome.PrintChromosome();
                PrintGrid.printNQueensGrid(chromosome);
                return chromosome;
            }
           
           
            // Select parents 
            List<Chromosome<Integer>> selectedChromosomes = selectionStrategy.select(population, numberToBeSelected, true);
            
        
            // Apply Crossover
            List<Chromosome<Integer>> newOffsprings = applyCrossover(selectedChromosomes);
        
     
            // Adjust mutation rate based on diversity
            double dynamicMutationRate = geneticParams.getMutationRate();
            double variance = calculateFitnessVariance(population);
            if (variance < 1.0) { // Threshold for low diversity 
                dynamicMutationRate = Math.min(dynamicMutationRate * 2, 0.5); // Double mutation rate, cap at 0.5
            }
            
            // Apply mutation to offspring
            for(int i = 0;i < newOffsprings.size();i++) {
                double randomNum = rand.nextDouble();
                if(randomNum < dynamicMutationRate) {
                    newOffsprings.set(i, mutationStrategy.mutate(newOffsprings.get(i)));    
                }

            }


            // Perform replacement 
            population = replacementStrategy.replace(population, newOffsprings, fitnessFunction, true);

            currentGeneration++;
           
        }


        System.out.println("No Solution Found in " + geneticParams.getGenerations() + " generations.");
        System.out.println("Best Solution Found: ");  
        Chromosome<Integer> solution = population.stream()
                .min(Comparator.comparingDouble(fitnessFunction::evaluate))
                .orElse(null);
        if (solution != null) {
            System.out.println("Chromosome: ");
            solution.PrintChromosome();
            PrintGrid.printNQueensGrid(solution);
        }
        return solution;

    }


    private List<Chromosome<Integer>> applyCrossover(List<Chromosome<Integer>> selectedChromosomes) {
        List<Chromosome<Integer>> newOffsprings = new ArrayList<>();
        int count = 0;
        for(int i = 0;i < selectedChromosomes.size() ;i += 2) {
                double randomNum =  rand.nextDouble();;
                if(randomNum < geneticParams.getCrossoverRate()) {
                    
                    Chromosome<Integer> firstParent = selectedChromosomes.get(i);
                    Chromosome<Integer> secondParent = selectedChromosomes.get((i + 1) % selectedChromosomes.size());
                   
                    if(i == 0 || (i + 1) % selectedChromosomes.size() == 0) count++;

                    // apply crossover
                    List<Chromosome<Integer>> offSprings = crossoverStrategy.crossOver(firstParent, secondParent);
                    newOffsprings.add(offSprings.get(0));
                    newOffsprings.add(offSprings.get(1));
                   
                }
               
        }
        // Odd Size Choose Best
        if(selectedChromosomes.size() % 2 != 0 && count == 2) {
            Chromosome<Integer> last = null;
           
            double fit1 = fitnessFunction.evaluate(newOffsprings.get(newOffsprings.size() - 1));
            double fit2 = fitnessFunction.evaluate(newOffsprings.get(0));
            
            // Bec Here we are minimization problem
            last = (fit1 <= fit2) ? newOffsprings.get(newOffsprings.size() - 1) : newOffsprings.get(0);
           
            newOffsprings.set(0, last);
            newOffsprings.remove(newOffsprings.size() - 1);
        }


        return newOffsprings;

        
        
    }


    private Chromosome<Integer> didReachSolution() {
        for (Chromosome<Integer> chromosome : population) {
            double fitness = fitnessFunction.evaluate(chromosome);
            if (fitness == 0) { 
                return chromosome;
            }
        }

        return null;
    }
    

    
    // Calculate population fitness variance to monitor diversity
    private double calculateFitnessVariance(List<Chromosome<Integer>> population) {
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