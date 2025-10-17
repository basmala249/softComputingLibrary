package FitnessFunctions;

import Chromosomes.Chromosome;

public interface InfeasibilityCheck<T> {
    boolean isInfeasible(Chromosome<T> chromosome);
}