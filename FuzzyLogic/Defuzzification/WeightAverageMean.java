package Defuzzification;

import java.util.List;

import GeneticAlgorithm.Utils.Pair;
import MemberFunction.IMemberFunction;

public class WeightAverageMean<T extends Number>  implements IDefuzzification {
  
    // public Double defuzzify(List<Double> membershipValues, List<IMemberFunction> fuzzySetValues) {

    //     Double crispValue = 0.0;
    //     Double crispValueDenimenator = 0.0;
    //     for(int i = 0; i < membershipValues.size(); i++) {
    //         Double membershipValue = membershipValues.get(i);
    //         IMemberFunction fuzzySet = fuzzySetValues.get(i);

    //         double centroid = 0.0;

    //         List<Double> points = fuzzySet.getPoints();
    //         for(Double point : points) {
    //             centroid += point.doubleValue();
    //         }
    //         centroid /= points.size();
    //         crispValue += membershipValue * centroid;
    //         crispValueDenimenator += membershipValue;
            
    //     }

      
    //     return crispValue/crispValueDenimenator;
    // }

    @Override
    public Pair defuzzify(List<IMemberFunction> fuzzySetValues) {
        Double crispValue = 0.0;
        Double crispValueDenimenator = 0.0;
        for(int i = 0; i < fuzzySetValues.size(); i++) {
           
            IMemberFunction fuzzySet = fuzzySetValues.get(i);
            Double membershipValue = fuzzySet.getMembership();

            double centroid = 0.0;

            List<Double> points = fuzzySet.getPoints();
            for(Double point : points) {
                centroid += point.doubleValue();
            }
            centroid /= points.size();
            crispValue += membershipValue * centroid;
            crispValueDenimenator += membershipValue;
            
        }
        return new Pair("", crispValue/crispValueDenimenator);
       
    }
    
}
