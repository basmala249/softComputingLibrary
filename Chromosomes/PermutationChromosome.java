package Chromosomes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PermutationChromosome implements Chromosome <Integer>{
    private int chromosomeLength;
    private List<Integer> chromosome;

    public PermutationChromosome(int chromosomeLength) {
        this.chromosomeLength = chromosomeLength;
        this.chromosome = new ArrayList<>(chromosomeLength);
        InitializeChromosome();
    }

    @Override
    public void InitializeChromosome() {
        chromosome.clear();
        for (int i = 0; i < chromosomeLength; i++) {
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
}
