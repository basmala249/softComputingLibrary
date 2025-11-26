package GeneticAlgorithm.Utils;
import java.util.Objects;

public class Pair {
    String first;
    double second;
    public Pair(String first, double second){
        this.first=first;
        this.second=second;
    }
    public String getFirst() {
        return first;
    }
    public double getSecond() {
        return second;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Double.compare(pair.second, second) == 0 &&
            first.equals(pair.first);
    }
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    public void setSecond(Double X){
        second = X;
    }
}
