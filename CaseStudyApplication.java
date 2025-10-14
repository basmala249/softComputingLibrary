public class CaseStudyApplication {
    public static void main(String[] args) {
        // Define the fitness function
        // FitnessFunction fitnessFunction = new FitnessFunction() {
        // @Override
        // public double evaluate(Chromosome individual) {
        // // Implement according to the chosen problem
        // }
        // };

        GeneticAlgorithmParameters geneticParams =
                new GeneticAlgorithmParameters(50,
                100,
                10,
                0.07,
                0.02);
        
        // Configure and run the GA imported from your library
        GeneticAlgorithmMethod ga_engine = new GeneticAlgorithmMethod(geneticParams);
        ga_engine.run();
        // Finally, get the best solution from the GA


        // trying onlyyy
//         Chromosome binaryChromosome = new BinaryChromosome(8);
//         binaryChromosome.InitializeChromosome();
//         binaryChromosome.PrintChromosome();
//
//        binaryChromosome.InitializeChromosome();
//        binaryChromosome.PrintChromosome();
 }
} 