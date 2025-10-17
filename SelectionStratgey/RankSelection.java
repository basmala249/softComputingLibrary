package SelectionStratgey;

import Chromosomes.*;
import FitnessFunctions.*;
import Utils.SearchHelpers;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RankSelection<T> implements SelectionInterface<T> {

    private Random random = new Random();

    @Override
    public List<Chromosome<T>> select(List<Chromosome<T>> chromosomes, int numberToBeSelected, boolean isMinimization) {
        
        int n = chromosomes.size();
        IFitnessFunction<T> fitnessFunction = chromosomes.get(0).getFitnessFunction();
        chromosomes.sort((ch1, ch2) -> Double.compare(fitnessFunction.evaluate(ch1), fitnessFunction.evaluate(ch2)));

        List<Integer> ranks = getRanks(chromosomes, isMinimization);
        List<Chromosome<T>> selectedChromosomes = new ArrayList<>();

        int upper_limit = (n * (n + 1) / 2) + 1;

        for(int i = 0; i < numberToBeSelected;i++) {
            int randomNum = random.nextInt(1, upper_limit);
            int targetChromosomeIndex = SearchHelpers.lowerBound(ranks, randomNum);
            selectedChromosomes.add(chromosomes.get(targetChromosomeIndex));


        }
        return selectedChromosomes;
    }

    private List<Integer> getRanks(List<Chromosome<T>> chromosomes, boolean isMinimization) {
        int n = chromosomes.size();
        List<Integer> ranks = new ArrayList<Integer>();
        int sum = 0;
        for(int i = 1; i <= n;i++) {
            sum += isMinimization ? i :(n - i + 1);
            ranks.add(sum);
        }
        return ranks;

    }
}

