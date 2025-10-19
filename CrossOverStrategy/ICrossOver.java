package CrossOverStrategy;
import Chromosomes.Chromosome;
import java.util.List;


public interface ICrossOver<T> {
    List<Chromosome<T>> crossOver(Chromosome<T> firstChromosome, Chromosome<T> secondChromosome);
}
