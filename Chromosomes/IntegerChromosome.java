package Chromosomes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IntegerChromosome implements Chromosome <Integer> {
    private int chromosomeLength;
    private List<Integer> chromosome;


    public IntegerChromosome(int chromosomeLength) {
        this.chromosomeLength = chromosomeLength;
        chromosome = new ArrayList<>(chromosomeLength);
        for(int i = 0; i < chromosomeLength; i++) {
            chromosome.add(0);
        }
    }
    @Override
    public void InitializeChromosome() {
        chromosome = new ArrayList<>(chromosomeLength);
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
}
