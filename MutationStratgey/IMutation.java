package MutationStratgey;

import Chromosomes.Chromosome;

public interface IMutation<T extends Chromosome> {
    T mutate(T chromosome);
}