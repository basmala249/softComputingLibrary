package GeneticAlgorithm.Chromosomes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import GeneticAlgorithm.FitnessFunctions.IFitnessFunction;
public class PermutationChromosome implements Chromosome <Integer>{
    private int chromosomeLength;
    private List<Integer> chromosome;
    private IFitnessFunction<Integer> fitnessFunction;

    public PermutationChromosome(int chromosomeLength, IFitnessFunction<Integer> fitnessFunction) {
        this.chromosomeLength = chromosomeLength;
        this.fitnessFunction = fitnessFunction;
        InitializeChromosome();
    }

    @Override
    public void InitializeChromosome() {
        chromosome = new ArrayList<>();
        // fill from 1 to n then Shuffle
        for (int i = 1; i <= chromosomeLength; i++) {
            chromosome.add(i);
        }
        Collections.shuffle(chromosome);
    }

    @Override
    public void PrintChromosome() {
        System.out.println(chromosome);
    }

    @Override
    public void setIndex(int index, Integer value) {
        chromosome.set(index, value);
    }

    @Override
    public Integer getIndex(int index) {
        return chromosome.get(index);
    }
    @Override
    public int getSize() {  
        return chromosomeLength;
    }
    @Override
    public Chromosome<Integer> copy() {
        // Copy Constructor
        Chromosome<Integer> newChromosome = new PermutationChromosome(this.chromosomeLength, this.fitnessFunction);
        for(int i = 0; i < this.chromosomeLength; i++) {
            newChromosome.setIndex(i, this.getIndex(i));
        }
        return newChromosome;
    }
    @Override
    public boolean contains(Integer value) {
        return chromosome.contains(value);
    }
    @Override
    public IFitnessFunction<Integer> getFitnessFunction() {
        return fitnessFunction;
    }
}
