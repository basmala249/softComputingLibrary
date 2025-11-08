package Problem;

import GeneticAlgorithm.Chromosomes.Chromosome;

public abstract class GeneticAlgorithmMethod {
    GeneticAlgorithmParameters geneticParams;
  
    public GeneticAlgorithmMethod(GeneticAlgorithmParameters geneticParams) {
        this.geneticParams = geneticParams;
       
    }
    abstract <T> Chromosome<T> run();
     
}
