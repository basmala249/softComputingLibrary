package GeneticAlgorithm.SelectionStratgey;

import java.util.List;

import GeneticAlgorithm.Chromosomes.Chromosome;

public interface SelectionInterface <T> {
    List<Chromosome<T>> select(List<Chromosome<T>> chromosomes, int numberToBeSelected , boolean isMinimization);
}
