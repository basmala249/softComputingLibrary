package FitnessFunctions;

import Chromosomes.Chromosome;

public interface IFitnessFunction<T> {
    double evaluate(Chromosome<T> chromosome);
}