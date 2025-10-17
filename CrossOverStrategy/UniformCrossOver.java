package CrossOverStrategy;

import Chromosomes.Chromosome;
import java.util.ArrayList;
import java.util.List;

public class UniformCrossOver<T> implements ICrossOver<T> {
    @Override
    public List<Chromosome<T>> crossOver(List<Chromosome<T>> chromosomes , boolean isMinimization) {
        List<Chromosome<T>> offspring = new ArrayList<>();
        if(chromosomes.size() % 2 != 0){
            offspring.add(chromosomes.get(chromosomes.size()-1));
            chromosomes.remove(chromosomes.size()-1);
        }
        for(int i=0;i<chromosomes.size();i+=2){
            Chromosome<T> parent1 = chromosomes.get(i);
            Chromosome<T> parent2 = chromosomes.get(i+1);
            int length = parent1.getSize();
            for(int j=0;j<length;j++){
                if(Math.random() < 0.5){
                    T temp = parent1.getIndex(j);
                    parent1.setIndex(j,parent2.getIndex(j));
                    parent2.setIndex(j,temp);
                }
            }
            offspring.add(parent1);
            offspring.add(parent2);
        }
        return offspring;
    }
}
