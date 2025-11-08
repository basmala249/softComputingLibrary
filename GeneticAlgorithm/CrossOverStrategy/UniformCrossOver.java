package GeneticAlgorithm.CrossOverStrategy;

import java.util.ArrayList;
import java.util.List;

import GeneticAlgorithm.Chromosomes.Chromosome;

public class UniformCrossOver<T> implements ICrossOver<T> {
    @Override
    public List<Chromosome<T>> crossOver(Chromosome<T> firstChromosome, Chromosome<T> secondChromosome) {
        List<Chromosome<T>> offspring = new ArrayList<>();
          
        int length = firstChromosome.getSize();
        for(int j=0;j<length;j++){
            if(Math.random() < 0.5){
                T temp = firstChromosome.getIndex(j);
                firstChromosome.setIndex(j,secondChromosome.getIndex(j));
                secondChromosome.setIndex(j,temp);
            }
        }
        offspring.add(firstChromosome);
        offspring.add(secondChromosome);
        
        return offspring;
    }
}
