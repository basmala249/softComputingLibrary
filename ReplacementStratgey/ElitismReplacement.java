package ReplacementStratgey;

import Chromosomes.Chromosome;
import FitnessFunctions.IFitnessFunction;

import java.util.ArrayList;
import java.util.List;

public class ElitismReplacement<T> implements IReplacement<T> {



    @Override
    public List<Chromosome<T>> replace(List<Chromosome<T>> oldPopulation,
                                       List<Chromosome<T>> newOffsprings,
                                       IFitnessFunction<T> fitnessFunction,
                                       boolean minimize) {

        int n = oldPopulation.size();
        int eliteCount = n - newOffsprings.size();

        // Sort the old population by fitness
        if (!minimize) {
            oldPopulation.sort((ch1, ch2) -> 
                Double.compare(fitnessFunction.evaluate(ch2), fitnessFunction.evaluate(ch1)));
        } else {
            oldPopulation.sort((ch1, ch2) -> 
                Double.compare(fitnessFunction.evaluate(ch1), fitnessFunction.evaluate(ch2)));
        }


        // Elitism: Keep top 'eliteCount' chromosomes
        for (int i = 0; i < eliteCount && i < n; i++) {
            newOffsprings.add(oldPopulation.get(i));
        }
        

        return newOffsprings;
    }
}