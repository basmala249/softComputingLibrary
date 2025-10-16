package Chromosomes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IntegerChromosome implements Chromosome <Integer> {
    private int chromosomeLength;
    private List<Integer> chromosome;


    public IntegerChromosome(int chromosomeLength) {
        this.chromosomeLength = chromosomeLength;
        InitializeChromosome();
    }
    @Override
    public void InitializeChromosome() {
        chromosome = new ArrayList<>();
        for(int i = 0; i < chromosomeLength; i++) {
            Random rand = new Random();
            int r = rand.nextInt();
            chromosome.add(r);
        }
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
        Chromosome<Integer> newChromosome = new IntegerChromosome(this.chromosomeLength);
        for(int i = 0; i < this.chromosomeLength; i++) {
            newChromosome.setIndex(i, this.getIndex(i));
        }
        return newChromosome;
    }
    @Override
    public boolean contains(Integer value) {
        return chromosome.contains(value);
    }
}
