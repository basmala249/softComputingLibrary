package GeneticAlgorithm.FitnessFunctions;

import GeneticAlgorithm.Chromosomes.Chromosome;

public interface IFitnessFunction<T> {
    double evaluate(Chromosome<T> chromosome);
}