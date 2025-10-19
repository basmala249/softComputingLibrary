package ReplacementStratgey;

import Chromosomes.Chromosome;
import FitnessFunctions.IFitnessFunction;

import java.util.ArrayList;
import java.util.List;

public class ElitismReplacement<T> implements IReplacement<T> {

    private final int eliteCount;

    public ElitismReplacement(int eliteCount) {
        this.eliteCount = eliteCount; // Number of elites to keep
    }

    // Calculate Hamming distance between two chromosomes
    private int hammingDistance(Chromosome<T> c1, Chromosome<T> c2) {
        int distance = 0;
        for (int i = 0; i < c1.getSize(); i++) {
            if (!c1.getIndex(i).equals(c2.getIndex(i))) {
                distance++;
            }
        }
        return distance;
    }

    @Override
    public List<Chromosome<T>> replace(List<Chromosome<T>> oldPopulation,
                                       List<Chromosome<T>> newOffsprings,
                                       IFitnessFunction<T> fitnessFunction,
                                       boolean minimize) {

        int n = oldPopulation.size();

        // Sort the old population by fitness
        if (!minimize) {
            oldPopulation.sort((ch1, ch2) -> 
                Double.compare(fitnessFunction.evaluate(ch2), fitnessFunction.evaluate(ch1)));
        } else {
            oldPopulation.sort((ch1, ch2) -> 
                Double.compare(fitnessFunction.evaluate(ch1), fitnessFunction.evaluate(ch2)));
        }


        // Elitism: Keep top 'eliteCount' chromosomes
        List<Chromosome<T>> newPopulation = new ArrayList<>();
        for (int i = 0; i < eliteCount && i < n; i++) {
            newPopulation.add(oldPopulation.get(i));
        }

        // Crowding: Replace most similar non-elite individuals with offspring
        List<Chromosome<T>> nonElites = new ArrayList<>(oldPopulation.subList(eliteCount, n));
        for (Chromosome<T> offspring : newOffsprings) {
            if (newPopulation.size() < n && !nonElites.isEmpty()) {
                // Find the non-elite individual most similar to the offspring
                Chromosome<T> mostSimilar = nonElites.stream()
                    .min((c1, c2) -> Integer.compare(hammingDistance(c1, offspring), hammingDistance(c2, offspring)))
                    .get();
                newPopulation.add(offspring);
                nonElites.remove(mostSimilar);
            }
        }

        // Fill remaining spots with non-elites if needed
        for (Chromosome<T> nonElite : nonElites) {
            if (newPopulation.size() < n) {
                newPopulation.add(nonElite);
            }
        }

        

        return newPopulation;
    }
}