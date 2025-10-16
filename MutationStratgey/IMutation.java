package MutationStratgey;

import Chromosomes.Chromosome;

public interface IMutation<T extends Chromosome<T>> {
    Chromosome<T> mutate(Chromosome<T> chromosome);
}