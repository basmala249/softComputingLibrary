package MutationStratgey;

import Chromosomes.Chromosome;

import java.util.Random;


public interface FloatMutationStrategy<T extends Number> extends IMutation<T> {
    @Override
    public default Chromosome<T> mutate(Chromosome<T> chromosome) {
        int length = chromosome.getSize();
        Random random = new Random();
        double L = 0, R = 100; /// the gene range is [0.0, 100.0] you can change it if you want
        for(int i = 0; i < length; ++i) {
            double val = chromosome.getIndex(i).doubleValue();
            double r = random.nextDouble();
            double mx = r <= 0.5 ? val - L : R - val;
            double r1 = random.nextDouble() * mx;
            double newVal = r <= 0.5 ? val - r1 : val + r1;
            chromosome.setIndex(i, (T) Double.valueOf(newVal));
        }
        return chromosome;
    }
}