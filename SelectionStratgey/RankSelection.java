package SelectionStratgey;

import Chromosomes.Chromosome;

import java.util.List;

public class RankSelection<T extends Chromosome<T>> implements SelectionInterface<T> {

    @Override
    public List<Chromosome<T>> select(List<Chromosome<T>> chromosomes, int numberToBeSelected , boolean isMinimization) {
        return null;
    }
}

