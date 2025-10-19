package Chromosomes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import FitnessFunctions.IFitnessFunction;

public class FloatChromosome implements Chromosome <Double>{
    private int chromosomeLength;
    private List<Double> chromosome;
    private IFitnessFunction<Double> fitnessFunction;
    private double min;
    private double max;

    public FloatChromosome(int chromosomeLength , double min, double max, IFitnessFunction<Double> fitnessFunction) {
        this.chromosomeLength = chromosomeLength;
        this.fitnessFunction = fitnessFunction;
        this.min = min;
        this.max = max;
        InitializeChromosome();
    }
    @Override
    public void InitializeChromosome() {
        chromosome = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < chromosomeLength; i++) {
            double r = rand.nextDouble() * (max - min) + min;
            chromosome.add(r);
        }
        /// doing that because the max value will never appear in the above loop.
        /// rand.nextDouble returns number less than 1. So I will never get max.
        int idx = rand.nextInt(chromosomeLength);
        chromosome.set(idx, max);
    }

    @Override
    public void PrintChromosome() {
        System.out.println(chromosome);
    }
    @Override
    public void setIndex(int index, Double value) {
        chromosome.set(index, value);
    }
    @Override
    public Double getIndex(int index) {
        return chromosome.get(index);
    }
    @Override
    public int getSize() {
        return chromosomeLength;
    }   
    @Override
    public Chromosome<Double> copy() {
        Chromosome<Double> newChromosome = new FloatChromosome(this.chromosomeLength , this.min, this.max, this.fitnessFunction);
        for(int i = 0; i < this.chromosomeLength; i++)
            newChromosome.setIndex(i, this.getIndex(i));
        return newChromosome;
    }
    @Override
    public boolean contains(Double value) {
        return chromosome.contains(value);
    }
    @Override
    public IFitnessFunction<Double> getFitnessFunction() {
        return fitnessFunction;
    }
}
