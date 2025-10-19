package CrossOverStrategy;
import Chromosomes.Chromosome;
import java.util.List;


public interface ICrossOver<T> {
    List<Chromosome<T>> crossOver(List<Chromosome<T>> chromosomes , boolean isMinimization);
}
