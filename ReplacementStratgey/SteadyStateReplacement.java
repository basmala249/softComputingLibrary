package ReplacementStratgey;
import Chromosomes.Chromosome;
import FitnessFunctions.IFitnessFunction;

import java.util.List;


public class SteadyStateReplacement<T> implements IReplacement<T> {

<<<<<<< HEAD
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
=======
    @Override
    public List<Chromosome<T>> replace(List<Chromosome<T>> oldPopulation, 
    List<Chromosome<T>> newOffSprings, IFitnessFunction<T> fitnessFunction, boolean minimize) {
//        int k = newOffSprings.size();
//        int n = oldPopulation.size();
//
//        if(!minimize)
//           oldPopulation.sort((ch1, ch2) -> Double.compare(fitnessFunction.evaluate(ch2), fitnessFunction.evaluate(ch1)));
//        else
//           oldPopulation.sort((ch1, ch2) -> Double.compare(fitnessFunction.evaluate(ch1), fitnessFunction.evaluate(ch2)));
//
//        for(int i = 0;i < oldPopulation.size();i++) {
//            System.out.print("Debug>> Old Population before Replacement :: " + " Fitness:: " + fitnessFunction.evaluate(oldPopulation.get(i)));
//            oldPopulation.get(i).PrintChromosome();
//
//        }
//
//        for(int i = 0;i < newOffSprings.size();i++) {
//            System.out.print("Debug>> New Off Springs before Replacement :: " + " Fitness:: " + fitnessFunction.evaluate(newOffSprings.get(i)));
//            newOffSprings.get(i).PrintChromosome();
//
//        }
//
//
//        for(int i = 0;i < n - k;i++) {
//           newOffSprings.add(oldPopulation.get(i));
//        }
//
//        for(int i = 0;i < newOffSprings.size();i++) {
//            System.out.print("Debug>> New Population After Replacement :: " + " Fitness:: " + fitnessFunction.evaluate(newOffSprings.get(i)));
//            newOffSprings.get(i).PrintChromosome();
//        }
//
//
//        return newOffSprings;

        int n = oldPopulation.size();
        int replaceCount = Math.min(newOffSprings.size(), n / 10); // replace at most 10%
        for (int i = 0; i < replaceCount; i++) {
            Chromosome<T> offspring = newOffSprings.get(i);
            oldPopulation.removeLast();
            oldPopulation.add(offspring);
        }

        return oldPopulation;
    }
>>>>>>> 7dc59c6 (Fix typo in condition and use a local array in InversionMutation instead of modifying the main array.)
    
}
