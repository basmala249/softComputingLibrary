package GeneticAlgorithm.MutationStratgey;

import GeneticAlgorithm.Chromosomes.Chromosome;

public interface IMutation<T> {
    Chromosome<T> mutate(Chromosome<T> chromosome);
}