package FitnessFunctions;

import Chromosomes.Chromosome;

public class NQueensInfeasibilityCheck implements InfeasibilityCheck <Integer> {
    private int N;

    public NQueensInfeasibilityCheck(int N) {
        this.N = N;
    }

    @Override
    public boolean isInfeasible(Chromosome<Integer> chromosome) {
        int conflicts = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // queens attack each other if they share a diagonal
                if (Math.abs(chromosome.getIndex(i) - chromosome.getIndex(j)) == Math.abs(i - j)) {
                    conflicts++;
                }
            }
        }
        return conflicts > 0;
    }
}