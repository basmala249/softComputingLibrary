package Problem;

import java.util.ArrayList;
import java.util.List;

import Chromosomes.*;
import CrossOverStrategy.*;
import FitnessFunctions.*;
import MutationStratgey.*;
import ReplacementStratgey.*;
import SelectionStratgey.*;
public class NQueensGeneticAlgorithmImplement extends GeneticAlgorithmMethod {
    
    public NQueensGeneticAlgorithmImplement(GeneticAlgorithmParameters geneticParams) {
        super(geneticParams);
    }
    @Override
    void run() {
        int currentGeneration = 0;
        List<Chromosome<Integer>> population = new ArrayList<>();
        IFitnessFunction<Integer> fitnessFunction = new N_QueensCaseStudyFitnessFunction(8);

        for(int i = 0;i < geneticParams.getPopulationSize();i++) {
            Chromosome<Integer> newChromosome = new PermutationChromosome(geneticParams.getChromosomeLength(), fitnessFunction);
            population.add(newChromosome);
        }
      
        SelectionInterface<Integer> selectionStrategy = new RankSelection<Integer>();
        ICrossOver<Integer> crossoverStrategy = new NPointCrossOver<Integer>(2);
        IMutation<Integer> mutationStrategy = new InsertMutationStrategy<Integer>();
        IReplacement<Integer> replacementStrategy = new SteadyStateReplacement<Integer>();

        while(currentGeneration < geneticParams.getGenerations()){

    
            List<Chromosome<Integer>> selectedChromosomes = selectionStrategy.select(population, geneticParams.getPopulationSize(), false);

            List<Chromosome<Integer>> newPopulation = new ArrayList<>();
        
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
                    newPopulation.add(offSprings.get(0));
                    newPopulation.add(offSprings.get(1));

                    System.out.println("Debug>> Crossover Applied ");
                    System.out.print("Debug>> Offspring1:: ");
                    offSprings.get(0).PrintChromosome();
                    System.out.print("Debug>> Offspring2:: ");
                    offSprings.get(1).PrintChromosome();
                   
                }
               
            }

            for(int i = 0;i < newPopulation.size();i++) {
                double randomNum = getRandomNumber();
                if(randomNum < geneticParams.getMutationRate()) {
                    newPopulation.set(i, mutationStrategy.mutate(newPopulation.get(i)));    
                }

            }

            
            population = replacementStrategy.replace(population, newPopulation, fitnessFunction, true);

            currentGeneration++;
        }
            
        
    }
    
}
