package FitnessFunctions;
import Chromosomes.Chromosome;

public class N_QueensCaseStudyFitnessFunction implements IFitnessFunction<Chromosome<Integer>> {
    private final int n;

    public N_QueensCaseStudyFitnessFunction(int n) {
        this.n = n;
    }

    @Override
    public double evaluate(Chromosome<Integer> chromosome) {
        double fitness = 0.0 ;
        //Implement Fitness Function

        return fitness;
    }
    
}
