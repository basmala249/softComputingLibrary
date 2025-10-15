package MutationStratgey;

import Chromosomes.Chromosome;

public interface IMutation<T extends Chromosome<T>> {
    T mutate(T chromosome);
}