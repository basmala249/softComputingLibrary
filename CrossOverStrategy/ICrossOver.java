package CrossOverStrategy;

import Chromosomes.Chromosome;

import java.util.List;

public interface ICrossOver {
    List<Chromosome> crossOver(List<Chromosome> chromosomes);
}
