package FitnessFunctions;
import Chromosomes.Chromosome;

public class N_QueensCaseStudyFitnessFunction implements IFitnessFunction<Chromosome<Integer>> {
    private final int N;

    public N_QueensCaseStudyFitnessFunction(int N) {
        this.N = N;
    }

    @Override
    public double evaluate(Chromosome<Integer> chromosome) {
        double fitness = 0.0 ;
        //Implement Fitness Function
        int conflicts = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // queens attack each other if they share a diagonal
                if (Math.abs(chromosome.getIndex(i) - chromosome.getIndex(j)) == Math.abs(i - j)) {
                    conflicts++;
                }
            }
        }
        int maxPairs = N * (N - 1) / 2;
        fitness = maxPairs - conflicts;
        return fitness;
    }
    
}
