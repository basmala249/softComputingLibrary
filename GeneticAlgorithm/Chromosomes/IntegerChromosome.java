package GeneticAlgorithm.Chromosomes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import GeneticAlgorithm.FitnessFunctions.IFitnessFunction;

public class IntegerChromosome implements Chromosome <Integer> {
    private int chromosomeLength;
    private List<Integer> chromosome;
    private IFitnessFunction<Integer> fitnessFunction;
    private int min;
    private int max;

    public IntegerChromosome(int chromosomeLength , int min, int max, IFitnessFunction<Integer> fitnessFunction) {
        this.chromosomeLength = chromosomeLength;
        this.fitnessFunction = fitnessFunction;
        this.min = min;
        this.max = max;
        InitializeChromosome();
    }
    @Override
    public void InitializeChromosome() {
        Random rand = new Random();
        chromosome = new ArrayList<>();
        for(int i = 0; i < chromosomeLength; i++) {
            int r = ((int)(rand.nextDouble() * (max - min + 1)) )+ min;
            chromosome.add(r);
        }
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
        Chromosome<Integer> newChromosome = new IntegerChromosome(this.chromosomeLength , this.min, this.max, this.fitnessFunction);
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
