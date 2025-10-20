package MutationStratgey;

import Chromosomes.Chromosome;

public class InversionMutationStrategy <T> implements IMutation<T> {

    @Override
    public Chromosome<T> mutate(Chromosome<T> chromosome) {
        Chromosome<T> mutated = chromosome.copy();
        int length = mutated.getSize();
        int index1 = (int) (Math.random() * length);
        int index2 = (int) (Math.random() * length);
        while (index2 == index1){
            index2 = (int) (Math.random() * length);
        }
        /// index1 always should be less than index2
        if(index2 < index1){
            int t = index1;
            index1 = index2;
            index2 = t;
        }
        while (index1<index2){
            T temp = mutated.getIndex(index1);
            mutated.setIndex(index1,mutated.getIndex(index2));
            mutated.setIndex(index2,temp);
            index1++;
            index2--;
        }

        return mutated;
    }
}
