package ReplacementStratgey;
import Chromosomes.Chromosome;
import FitnessFunctions.IFitnessFunction;

import java.util.List;


public class SteadyStateReplacement<T> implements IReplacement<T> {

    @Override
    public List<Chromosome<T>> replace(List<Chromosome<T>> oldPopulation, 
    List<Chromosome<T>> newOffSprings, IFitnessFunction<T> fitnessFunction, boolean minimize) {
        int k = newOffSprings.size();
        int n = oldPopulation.size();
      
        if(!minimize) 
           oldPopulation.sort((ch1, ch2) -> Double.compare(fitnessFunction.evaluate(ch2), fitnessFunction.evaluate(ch1)));
        else 
           oldPopulation.sort((ch1, ch2) -> Double.compare(fitnessFunction.evaluate(ch1), fitnessFunction.evaluate(ch2)));

        for(int i = 0;i < oldPopulation.size();i++) {
            System.out.print("Debug>> Old Population before Replacement :: " + " Fitness:: " + fitnessFunction.evaluate(oldPopulation.get(i)));
            oldPopulation.get(i).PrintChromosome();
            
        }
        for(int i = 0;i < n - k;i++) {
           newOffSprings.add(oldPopulation.get(i));
        }

        for(int i = 0;i < newOffSprings.size();i++) {
            System.out.print("Debug>> New Population After Population :: " + " Fitness:: " + fitnessFunction.evaluate(newOffSprings.get(i)));
            newOffSprings.get(i).PrintChromosome();
        }
        
       
        return newOffSprings;

    }
    
}
