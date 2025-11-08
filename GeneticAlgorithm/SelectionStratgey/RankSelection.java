package GeneticAlgorithm.SelectionStratgey;

import GeneticAlgorithm.Chromosomes.*;
import GeneticAlgorithm.FitnessFunctions.*;
import GeneticAlgorithm.Utils.SearchHelpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RankSelection<T> implements SelectionInterface<T> {

    private Random random = new Random();

    @Override
    public List<Chromosome<T>> select(List<Chromosome<T>> chromosomes, int numberToBeSelected, boolean isMinimization) {
        
        List<Chromosome<T>> copyOfChromosomes = new ArrayList<>(chromosomes);
        int n = copyOfChromosomes.size();
        // Sort chromosomes based on fitness
        IFitnessFunction<T> fitnessFunction = copyOfChromosomes.get(0).getFitnessFunction();
        copyOfChromosomes.sort((ch1, ch2) -> Double.compare(fitnessFunction.evaluate(ch2), fitnessFunction.evaluate(ch1)));

        // If the problem is minimization, reverse the sorted list
        // Bec we want the worst to have the lowest rank
        if(isMinimization) 
           Collections.reverse(copyOfChromosomes);

        // Get Cumulative Ranks of chromsomes
        List<Integer> ranks = getRanks(copyOfChromosomes);
        List<Chromosome<T>> selectedChromosomes = new ArrayList<>();

        int upper_limit = (n * (n + 1) / 2);

        // Select chromosomes based on ranks
        for(int i = 0; i < numberToBeSelected;i++) {
            int randomNum = random.nextInt(upper_limit) + 1;
            int targetChromosomeIndex = SearchHelpers.lowerBound(ranks, randomNum);
            selectedChromosomes.add(copyOfChromosomes.get(targetChromosomeIndex));
        }
           
        return selectedChromosomes;
    }

    private List<Integer> getRanks(List<Chromosome<T>> chromosomes) {
        int n = chromosomes.size();
        List<Integer> ranks = new ArrayList<Integer>();
        int sum = 0;
        for(int i = 1; i <= n;i++) {
            sum += i;
            ranks.add(sum);
        }
        return ranks;

    }
}

