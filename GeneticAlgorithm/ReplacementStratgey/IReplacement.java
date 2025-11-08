package GeneticAlgorithm.ReplacementStratgey;

import GeneticAlgorithm.Chromosomes.Chromosome;
import GeneticAlgorithm.FitnessFunctions.IFitnessFunction;

import java.util.List;

public interface IReplacement<T> {
    List<Chromosome<T>> replace(List<Chromosome<T>> oldPopulation, List<Chromosome<T>> newPopulation,IFitnessFunction<T> fitnessFunction, boolean minimize);
}