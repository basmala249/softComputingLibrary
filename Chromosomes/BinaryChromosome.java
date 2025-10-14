package Chromosomes;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryChromosome implements Chromosome {
    private Random random = new Random();
    private int chromosomeLength;
    private List<Integer> chromosome;


    public BinaryChromosome(int chromosomeLength) {
        this.chromosomeLength = chromosomeLength;
        chromosome = new ArrayList<>(chromosomeLength);
        for(int i = 0; i < chromosomeLength; i++) {
            chromosome.add(0);
        }
    }

    @Override
    public void InitializeChromosome() {

        for(int i = 0;i < chromosomeLength;i++) {
                chromosome.set(i, random.nextBoolean() ? 1 : 0);
        }

    }

    @Override
    public void PrintChromosome() {
        for(int i = 0;i < chromosomeLength; i++) {
            System.out.print(chromosome.get(i));
        }
        System.out.println();
    }
}
