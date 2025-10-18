package Problem;

import java.util.Random;



public abstract class GeneticAlgorithmMethod {
    GeneticAlgorithmParameters geneticParams;
    Random rand = new Random();
    
    public GeneticAlgorithmMethod(GeneticAlgorithmParameters geneticParams) {
        this.geneticParams = geneticParams;
       
    }
    abstract void run();

    protected double getRandomNumber() {
            return rand.nextDouble();
    }
    
     
}
