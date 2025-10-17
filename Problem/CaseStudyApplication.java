package Problem;
import FitnessFunctions.*;
//import Chromosomes.Chromosome;
public class CaseStudyApplication {
        public static IFitnessFunction<Integer> fitnessObject = new N_QueensCaseStudyFitnessFunction(8);
        public static void main(String[] args) {
                GeneticAlgorithmParameters geneticParams =
                      new GeneticAlgorithmParameters(6,
                                    2,
                                    8,
                                    0.8,
                                    0.02);
                GeneticAlgorithmMethod ga_engine = new GeneticAlgorithmMethod(geneticParams);
                ga_engine.run();
        
        }

} 