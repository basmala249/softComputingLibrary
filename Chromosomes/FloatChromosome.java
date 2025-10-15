package Chromosomes;

import java.util.ArrayList;
import java.util.List;

public class FloatChromosome implements Chromosome <Double>{
    private int chromosomeLength;
    private List<Double> chromosome;


    public FloatChromosome(int chromosomeLength) {
        this.chromosomeLength = chromosomeLength;
        chromosome = new ArrayList<>(chromosomeLength);
        for(int i = 0; i < chromosomeLength; i++) {
            chromosome.add(0.0);
        }
    }
    @Override
    public void InitializeChromosome() {

    }

    @Override
    public void PrintChromosome() {

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
}
