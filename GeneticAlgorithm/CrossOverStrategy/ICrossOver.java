package GeneticAlgorithm.CrossOverStrategy;
import java.util.List;

import GeneticAlgorithm.Chromosomes.Chromosome;


public interface ICrossOver<T> {
    List<Chromosome<T>> crossOver(Chromosome<T> firstChromosome, Chromosome<T> secondChromosome);
}
