package MutationStratgey;

import Chromosomes.Chromosome;


public interface FloatMutationStrategy<T> extends IMutation<T> {
    @Override
    public Chromosome<T> mutate(Chromosome<T> chromosome) ;
}
