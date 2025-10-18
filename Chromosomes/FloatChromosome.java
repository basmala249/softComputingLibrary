package Chromosomes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import FitnessFunctions.IFitnessFunction;

public class FloatChromosome implements Chromosome <Double>{
    private int chromosomeLength;
    private List<Double> chromosome;
    private IFitnessFunction<Double> fitnessFunction;

    public FloatChromosome(int chromosomeLength , IFitnessFunction<Double> fitnessFunction) {
        this.chromosomeLength = chromosomeLength;
        this.fitnessFunction = fitnessFunction;
        InitializeChromosome();
    }
    @Override
    public void InitializeChromosome() {
        chromosome = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < chromosomeLength; i++) {
            double r = rand.nextDouble();
            chromosome.add(r);
        }
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
        Chromosome<Double> newChromosome = new FloatChromosome(this.chromosomeLength , this.fitnessFunction);
        for(int i = 0; i < this.chromosomeLength; i++) {
            newChromosome.setIndex(i, this.getIndex(i));
        }
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
