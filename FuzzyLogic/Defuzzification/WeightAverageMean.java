package Defuzzification;

import java.util.List;

import MemberFunction.IMemberFunction;

public class WeightAverageMean<T extends Number>  {
    // needs the value returned from each rule (degree of membership)
    // needs also the fuzzy set that that value belongs to
    // Main idea only: lesa 3ayza ashoof lama elmamdani t5ls
    public Double defuzzify(List<Double> membershipValues, List<IMemberFunction> fuzzySetValues) {

        Double crispValue = 0.0;
        Double crispValueDenimenator = 0.0;
        for(int i = 0; i < membershipValues.size(); i++) {
            Double membershipValue = membershipValues.get(i);
            IMemberFunction fuzzySet = fuzzySetValues.get(i);

            double centroid = 0.0;

            List<Double> points = fuzzySet.getPoints();
            for(Double point : points) {
                centroid += point.doubleValue();
            }
            centroid /= points.size();
            crispValue += membershipValue * centroid;
            crispValueDenimenator += membershipValue;
            
        }

      
        return crispValue/crispValueDenimenator;
    }
    
}
