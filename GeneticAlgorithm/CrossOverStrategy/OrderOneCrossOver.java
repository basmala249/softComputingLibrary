package GeneticAlgorithm.CrossOverStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import GeneticAlgorithm.Chromosomes.Chromosome;



public class OrderOneCrossOver<T> implements ICrossOver <T> {
    @Override
    public List<Chromosome<T>> crossOver(Chromosome<T> firstChromosome, Chromosome<T> secondChromosome) {

        // CrossOver Process
        List<Chromosome<T>> childlrenGenes = createOffspring(firstChromosome, secondChromosome);
         
        return childlrenGenes;
    }
    private List<Chromosome<T>> createOffspring(Chromosome<T> parent1, Chromosome<T> parent2) {
        int size = parent1.getSize();
        Random rand = new Random();
        List<Chromosome<T>> childGenes = new ArrayList<>(); // return list
        
        int start = (int)(rand.nextDouble() * size); // get random start point
        int k = 1 + (int)(rand.nextDouble() * (size - start)) ;  // size of subset
        
        // Create child chromosomes initialized with nulls
        Chromosome<T> child1Genes = parent1.copy();
        Chromosome<T> child2Genes = parent2.copy();
       
        for(int i = 0; i < size; i++) {
            child1Genes.setIndex(i, null);
            child2Genes.setIndex(i, null);
        }
        // Copy 
        for(int i = start; i <= Math.min((int)size - 1 , (int)start + k - 1); i++) {
            child1Genes.setIndex(i, parent1.getIndex(i));
            child2Genes.setIndex(i, parent2.getIndex(i));
        }
        // Fill in the remaining genes from the other parent
        int currentIndex1 = (start + k) % size;
        int current = currentIndex1;
        while(child1Genes.contains(null)) {
            T gene = parent2.getIndex(currentIndex1);
            if(!child1Genes.contains(gene)) {
                child1Genes.setIndex(current, gene);
                current = (current + 1) % size;
            }
            currentIndex1 = (currentIndex1 + 1) % size;
        }
        int currentIndex2 = (start + k) % size;
        current = currentIndex2;
        while(child2Genes.contains(null)) {
            T gene = parent1.getIndex(currentIndex2);
            if(!child2Genes.contains(gene)) {
                child2Genes.setIndex(current, gene);
                current = (current + 1) % size;
            }
            currentIndex2 = (currentIndex2 + 1) % size;
        }
         childGenes.add(child1Genes);
         childGenes.add(child2Genes);
         
        return childGenes;
    }
    

}
