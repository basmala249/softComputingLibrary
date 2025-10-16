package SelectionStratgey;

import Chromosomes.Chromosome;

import java.util.List;

public interface SelectionInterface <T> {
    List<Chromosome<T>> select(List<Chromosome<T>> chromosomes, int numberToBeSelected , boolean isMinimization);
}
