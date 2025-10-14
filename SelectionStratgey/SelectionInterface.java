package SelectionStratgey;

import Chromosomes.Chromosome;

import java.util.List;

public interface SelectionInterface {

    List<Chromosome> select(List<Chromosome> chromosomes, int numberToBeSelected);
}
