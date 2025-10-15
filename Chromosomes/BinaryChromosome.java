package Chromosomes;


import java.util.ArrayList;
import java.util.List;
//import java.util.Random;

import java.util.ArrayList;
import java.util.List;

public class BinaryChromosome implements Chromosome  {
    private int chromosomeLength;
    private List<Integer> chromosome;

    public BinaryChromosome(int chromosomeLength) {
        this.chromosomeLength = chromosomeLength;
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

     
}
