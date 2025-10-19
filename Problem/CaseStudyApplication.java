package Problem;
import Chromosomes.FloatChromosome;
import FitnessFunctions.*;
//import Chromosomes.Chromosome;
public class CaseStudyApplication {
        public static IFitnessFunction<Integer> fitnessObject = new N_QueensCaseStudyFitnessFunction(8);
        public static void main(String[] args) {
                GeneticAlgorithmParameters geneticParams =
                      new GeneticAlgorithmParameters(6,
                                    100,
                                    8,
                                    0.5,
                                    0.02);
                GeneticAlgorithmMethod ga_engine = new NQueensGeneticAlgorithmImplement(geneticParams);
                ga_engine.run();

                // FloatChromosome floatChromosome = new FloatChromosome(5, -10.0, 10.0, null);
                // floatChromosome.PrintChromosome();

                //  FloatChromosome floatChromosome02 = new FloatChromosome(5, -10.0, 10.0, null);
                // floatChromosome02.PrintChromosome();
        }

} 