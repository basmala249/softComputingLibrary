package Problem;
import FitnessFunctions.*;
//import Chromosomes.Chromosome;
public class CaseStudyApplication {
        // Define the fitness Object
        public static IFitnessFunction<Integer> fitnessObject = new N_QueensCaseStudyFitnessFunction(8);
        public static void main(String[] args) {
        
        

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