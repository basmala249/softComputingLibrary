package SelectionStratgey;

import Chromosomes.Chromosome;

import java.util.List;

public interface SelectionInterface<T extends Chromosome<T>> {
    List<T> select(List<T> chromosomes, int numberToBeSelected);
}
