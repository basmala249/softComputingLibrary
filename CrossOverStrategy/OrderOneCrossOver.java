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
            
        }
        


        return offsprings;
    }

}
