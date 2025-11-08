package GeneticAlgorithm.FitnessFunctions;
import GeneticAlgorithm.Chromosomes.Chromosome;

public class KnapsackFitnessFunction implements IFitnessFunction<Integer> {
    private final int[] weights;
    private final int[] values;
    private final int capacity;

    public KnapsackFitnessFunction(int[] weights, int[] values, int capacity) {
        this.weights = weights;
        this.values = values;
        this.capacity = capacity;
    }

    @Override
    public double evaluate(Chromosome<Integer> chromosome) {
        int totalWeight = 0;
        int totalValue = 0;
        for (int i = 0; i < chromosome.getSize(); i++) {
            if (chromosome.getIndex(i) == 1) { // 1 means item is selected
                totalWeight += weights[i];
                totalValue += values[i];
            }
        }
        if (totalWeight > capacity) return 0; // penalty if over capacity
        return totalValue;
    }

    public double getMaxPossibleValue() {
        int sum = 0;
        for (int v : values) sum += v;
        return sum;
    }
}
