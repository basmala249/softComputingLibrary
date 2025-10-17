package Chromosomes;


import java.util.ArrayList;
import java.util.List;
//import java.util.Random;
import FitnessFunctions.IFitnessFunction;


public class BinaryChromosome implements Chromosome<Integer> {
    private int chromosomeLength;
    private List<Integer> chromosome;
    private IFitnessFunction<Integer> fitnessFunction;

    public BinaryChromosome(int chromosomeLength, IFitnessFunction<Integer> fitnessFunction) {
        this.chromosomeLength = chromosomeLength;
        this.fitnessFunction = fitnessFunction;
        chromosome = new ArrayList<>(chromosomeLength);
        for (int i = 0; i < chromosomeLength; i++) {
            chromosome.add(0);
        }
    }

    @Override
    public void InitializeChromosome() {

    }

    @Override
    public void PrintChromosome() {
         
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
        Chromosome<Integer> newChromosome = new BinaryChromosome(this.chromosomeLength, this.fitnessFunction);
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
