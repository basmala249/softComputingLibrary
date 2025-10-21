package SelectionStratgey;

import Chromosomes.Chromosome;
import FitnessFunctions.IFitnessFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RouletteWheelSelection<T> implements SelectionInterface<T> {


    @Override
    public List<Chromosome<T>> select(List<Chromosome<T>> chromosomes, int numberToBeSelected , boolean isMinimization) {
           
            List<Double> res = new ArrayList<>();
            List<Chromosome<T>> selected = new ArrayList<>();
            Long totalFitness = 0L;
        // Get Fitness Values and Total Fitness
        IFitnessFunction<T> fitnessFunction = chromosomes.get(0).getFitnessFunction();
        for (Chromosome<T> chromosome : chromosomes) {
            double fitnessValue = fitnessFunction.evaluate(chromosome);
            res.add(fitnessValue);
            totalFitness += (long) fitnessValue;
        }
            
            // Support Minimization Problems

            
            ///  no it make sense 
            double val = 0.0 ;
            for(int i = 0; i < chromosomes.size(); i++) {
                val = res.get(i) / totalFitness ;
                //val = customRound(val);
                val = (isMinimization ? 100 - val : val);
                val *= 100;
                res.set(i, val);
            }
            // Cumulative Sum
            for(int i = 1; i < chromosomes.size(); i++) {
                 res.set(i, res.get(i) + res.get(i - 1));
            }
            Random rand = new Random();
            double num = rand.nextDouble() * res.get(res.size() - 1);
            int indx = -1;
            // Select numberToBeSelected chromosomes based on Random numbers
            for(int i = 0; i < numberToBeSelected; i++) {
                 num = rand.nextDouble() * res.get(res.size() - 1);
                 indx = lowerBound(res, num); // O(log n)
                 selected.add(chromosomes.get(indx));
                 
            }
        return selected;
    }
    // Search Function for faster access
    int lowerBound(List<Double> arr, double target) {
        int left = 0, right = arr.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
}
