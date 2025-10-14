package SelectionStratgey;

import Chromosomes.Chromosome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouletteWheelSelection implements SelectionInterface {


    @Override
    public List<Chromosome> select(List<Chromosome> chromosomes, int numberToBeSelected) {
            List<Double> res = new ArrayList<>();
            List<Chromosome> selected = new ArrayList<>();
            Long totalFitness = 0L;
            for(int i = 0; i < chromosomes.size(); i++) {
                // res.add(calculateFitness(chromosomes.get(i)));
                //totalFitness += calculateFitness(chromosomes.get(i));
            }
            double val = 0.0 ;
            for(int i = 0; i < chromosomes.size(); i++) {
                val = (double) Math.round(totalFitness / res.get(i));
                val *= 100;
                val = 100 - val;
                res.set(i, val);
            }
            for(int i = 1; i < chromosomes.size(); i++) {
                 res.set(i, res.get(i) + res.get(i - 1));
            }
            Random rand = new Random();
            double num = rand.nextDouble() * 100;
            int indx = -1;
            for(int i = 0; i < numberToBeSelected; i++) {
                 num = rand.nextDouble() * 100;
                 indx = lowerBound(res, num);
                 selected.add(chromosomes.get(indx));
                 
            }
        return selected;
    }
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
