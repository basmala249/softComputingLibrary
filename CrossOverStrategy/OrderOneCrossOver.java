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
             

        }

        return offsprings;
    }
    private List<Chromosome<T>> createOffspring(Chromosome<T> parent1, Chromosome<T> parent2) {
        int size = parent1.getSize();
        List<Chromosome<T>> childGenes = new ArrayList<>();
        int start = (int)(Math.random() * size);
        return childGenes;
    }
    

}
