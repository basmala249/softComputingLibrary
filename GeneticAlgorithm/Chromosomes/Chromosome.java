package GeneticAlgorithm.Chromosomes;

import GeneticAlgorithm.FitnessFunctions.IFitnessFunction;

public interface  Chromosome <T> {
    
    void InitializeChromosome();
    void setIndex(int index, T value);
    T getIndex(int index);
    void PrintChromosome();
    int getSize();
    Chromosome<T> copy();
    boolean contains(T value);
    IFitnessFunction<T> getFitnessFunction();
}
