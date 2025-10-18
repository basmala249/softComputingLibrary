package CrossOverStrategy;

import Chromosomes.Chromosome;
import FitnessFunctions.IFitnessFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class OrderOneCrossOver<T> implements ICrossOver <T> {
    @Override
    public List<Chromosome<T>> crossOver(List<Chromosome<T>> chromosomes , boolean isMinimization) {
        System.out.println("Order One Crossover Applied ");
        System.out.println("Parent Chromosomes: ");
        for (Chromosome<T> chromosome : chromosomes) {
            System.out.print("Chromosome :: ");
            chromosome.PrintChromosome();
        }
        List<Chromosome<T>> offsprings = new ArrayList<Chromosome<T>>();
        for(int i = 0; i < chromosomes.size(); i += 2) {
            Chromosome<T> parent1 = chromosomes.get(i);
            Chromosome<T> parent2 = chromosomes.get((i + 1) % chromosomes.size());
            List<Chromosome<T>> childlrenGenes = createOffspring(parent1, parent2);
            offsprings.add(childlrenGenes.get(0));
            offsprings.add(childlrenGenes.get(1));
        }
        if(chromosomes.size() % 2 != 0) {
            Chromosome<T> last = null;
            IFitnessFunction<T> fitnessFunction = offsprings.get(0).getFitnessFunction();
            double fit1 = fitnessFunction.evaluate(offsprings.get(offsprings.size() - 1));
            double fit2 = fitnessFunction.evaluate(offsprings.get(0));
            if(isMinimization) {
             
                last = (fit1 <= fit2) ? offsprings.get(offsprings.size() - 1) : offsprings.get(0);
            }else{
                last = (fit1 >= fit2) ? offsprings.get(offsprings.size() - 1) : offsprings.get(0);
            }
            offsprings.set(0, last);
            offsprings.remove(offsprings.size() - 1);
        }

        return offsprings;
    }
    private List<Chromosome<T>> createOffspring(Chromosome<T> parent1, Chromosome<T> parent2) {
        int size = parent1.getSize();
        Random rand = new Random();
        List<Chromosome<T>> childGenes = new ArrayList<>(); // return list
        System.out.println("Chromosome Size: " + size);
        int start = (int)(rand.nextDouble() * size); // get random start point
        int k = (int)(rand.nextDouble() * (size - start)) ;  // size of subset
        System.out.println("start: " + start + " k: " + ( k));
        System.out.println("Parent1: ");
        parent1.PrintChromosome();
        System.out.println("Parent2: ");
        parent2.PrintChromosome();
        Chromosome<T> child1Genes = parent1.copy();
        Chromosome<T> child2Genes = parent2.copy();
       
        for(int i = 0; i < size; i++) {
            child1Genes.setIndex(i, null);
            child2Genes.setIndex(i, null);
        }
        for(int i = start; i <= Math.min((int)size - 1 , (int)start + k); i++) {
            child1Genes.setIndex(i, parent1.getIndex(i));
            child2Genes.setIndex(i, parent2.getIndex(i));
        }
        int currentIndex1 = (start + k + 1) % size;
        int current = currentIndex1;
        while(child1Genes.contains(null)) {
            T gene = parent2.getIndex(currentIndex1);
            if(!child1Genes.contains(gene)) {
                child1Genes.setIndex(current, gene);
                current = (current + 1) % size;
            }
            currentIndex1 = (currentIndex1 + 1) % size;
        }
        int currentIndex2 = (start + k + 1) % size;
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
         System.out.println("Child1: ");
         child1Genes.PrintChromosome();
         System.out.println("Child2: ");
         child2Genes.PrintChromosome();
        return childGenes;
    }
    

}
