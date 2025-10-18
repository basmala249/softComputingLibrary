package MutationStratgey;

import Chromosomes.Chromosome;

public class InversionMutationStrategy <T> implements IMutation<T> {

    @Override
    public Chromosome<T> mutate(Chromosome<T> chromosome) {
        int length = chromosome.getSize();
        int index1 = (int) (Math.random() * length);
        int index2 = (int) (Math.random() * length);
        while (index2 == index1){
            index2 = (int) (Math.random() * length);
        }
        if(index2 > index1){
            int t = index1;
            index1 = index2;
            index2 = t;
        }
        while (index1<index2){
            T temp = chromosome.getIndex(index1);
            chromosome.setIndex(index1,chromosome.getIndex(index2));
            chromosome.setIndex(index2,temp);
            index1++;
            index2--;
        }

        return chromosome;
    }
}
