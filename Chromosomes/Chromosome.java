package Chromosomes;

public interface  Chromosome <T>{
    
    void InitializeChromosome();
    void setIndex(int index, T value);
    T getIndex(int index);
    void PrintChromosome();
    int getSize();
    Chromosome<T> copy();
    boolean contains(T value);
}
