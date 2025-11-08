package GeneticAlgorithm.ReplacementStratgey;
import GeneticAlgorithm.Chromosomes.Chromosome;
import GeneticAlgorithm.FitnessFunctions.IFitnessFunction;

import java.util.List;


public class SteadyStateReplacement<T> implements IReplacement<T> {

   @Override
   public List<Chromosome<T>> replace(List<Chromosome<T>> oldPopulation, List<Chromosome<T>> newOffSprings, IFitnessFunction<T> fitnessFunction, boolean minimize) {
      int k = newOffSprings.size();
      int n = oldPopulation.size();
      
      // Sort old population based on fitness 
      // To delete the worst chromosomes
      if(!minimize) 
         oldPopulation.sort((ch1, ch2) -> Double.compare(fitnessFunction.evaluate(ch2), fitnessFunction.evaluate(ch1)));
      else 
         oldPopulation.sort((ch1, ch2) -> Double.compare(fitnessFunction.evaluate(ch1), fitnessFunction.evaluate(ch2)));

      // Keep the best n - k chromosomes from old population
      for(int i = 0;i < n - k;i++) {
         newOffSprings.add(oldPopulation.get(i));
      }
        
      return newOffSprings;

   }
    
}
