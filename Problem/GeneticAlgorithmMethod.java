package Problem;


public abstract class GeneticAlgorithmMethod {
    GeneticAlgorithmParameters geneticParams;
  
    public GeneticAlgorithmMethod(GeneticAlgorithmParameters geneticParams) {
        this.geneticParams = geneticParams;
       
    }
    abstract void run();
     
}
