package CrossOverStrategy;

import Chromosomes.Chromosome;

import java.util.ArrayList;
import java.util.List;

public class OrderOneCrossOver<T> implements ICrossOver <T> {
    @Override
    public List<Chromosome<T>> crossOver(List<Chromosome<T>> chromosomes) {
        List<Chromosome<T>> offsprings = new ArrayList<Chromosome<T>>();
         
        for(int i = 0; i < chromosomes.size(); i += 2) {
            Chromosome<T> parent1 = chromosomes.get(i);
            Chromosome<T> parent2 = chromosomes.get((i + 1) % chromosomes.size());
            List<Chromosome<T>> childlrenGenes = createOffspring(parent1, parent2);
            offsprings.add(childlrenGenes.get(0));
            offsprings.add(childlrenGenes.get(1));
        }
        if(chromosomes.size() % 2 != 0) {
             //calculate fitness for best chromosome for first and last

        }

        return offsprings;
    }
    private List<Chromosome<T>> createOffspring(Chromosome<T> parent1, Chromosome<T> parent2) {
        int size = parent1.getSize();
        List<Chromosome<T>> childGenes = new ArrayList<>();
        int start = (int)(Math.random() * size);
        int k = (int)(Math.random() * (size - start)) ;  
        List<T> child1Genes = new ArrayList<>(size);
        List<T> child2Genes = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            child1Genes.add(null);
            child2Genes.add(null);
        }
        for(int i = start; i < Math.min((int)size , (int)start + k); i++) {
            child1Genes.set(i, parent1.getIndex(i));
            child2Genes.set(i, parent2.getIndex(i));
        }
        int currentIndex1 = (start + k) % size;
        int current = currentIndex1;
        while(child1Genes.contains(null)) {
            T gene = parent2.getIndex(currentIndex1);
            if(!child1Genes.contains(gene)) {
                child1Genes.set(current, gene);
                current = (current + 1) % size;
            }
            currentIndex1 = (currentIndex1 + 1) % size;
        }
        int currentIndex2 = (start + k) % size;
        current = currentIndex2;
        while(child2Genes.contains(null)) {
            T gene = parent1.getIndex(currentIndex2);
            if(!child2Genes.contains(gene)) {
                child2Genes.set(current, gene);
                current = (current + 1) % size;
            }
            currentIndex2 = (currentIndex2 + 1) % size;
        }
        // childGenes.add(new Chromosome<T>(child1Genes));
        // childGenes.add(new Chromosome<T>(child2Genes));
        return childGenes;
    }
    

}
