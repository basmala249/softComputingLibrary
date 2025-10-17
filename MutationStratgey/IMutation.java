package MutationStratgey;

import Chromosomes.Chromosome;

public interface IMutation<T> {
    Chromosome<T> mutate(Chromosome<T> chromosome);
}