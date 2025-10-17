package MutationStratgey;

import Chromosomes.Chromosome;
//import Chromosomes.FloatChromosome;

// Might need to split this class into two classes (uniform & non-uniform)
// depending on our implementation


public interface FloatMutationStrategy<T extends Chromosome<T>> extends IMutation<T> {
    @Override
    public Chromosome<T> mutate(Chromosome<T> chromosome) ;
}
