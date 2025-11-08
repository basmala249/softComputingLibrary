package GeneticAlgorithm.MutationStratgey;

import GeneticAlgorithm.Chromosomes.Chromosome;

public class InsertMutationStrategy<T> implements IMutation<T> {
    @Override
    public Chromosome<T> mutate(Chromosome<T> chromosome) {
        int lenth = chromosome.getSize();
        int index1 = (int) (Math.random() * lenth);
        int index2 = (int) (Math.random() * lenth);
        while (index1 == index2) {
            index2 = (int) (Math.random() * lenth);
        }
        if (index1 > index2) {
            int temp = index1;
            index1 = index2;
            index2 = temp;
        }
        for(int i=index2;i>index1;i--){
           T value = chromosome.getIndex(i);
           chromosome.setIndex(i,chromosome.getIndex(i-1));
           chromosome.setIndex(i-1,value);
        }
       return chromosome;
    }
}
