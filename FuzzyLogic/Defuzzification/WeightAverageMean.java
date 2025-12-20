package FuzzyLogic.Defuzzification;
import java.util.List;

import GeneticAlgorithm.Utils.Pair;
import FuzzyLogic.MemberFunction.IMemberFunction;
import java.util.Map;
import java.util.Set;

import FuzzyLogic.FuzzySet.FuzzySet;

public class WeightAverageMean<T extends Number> extends IDefuzzification {

    @Override
    public Pair defuzzify(FuzzySet fuzzySet, Map<String, Set<Pair>> fuzzifyResults) {

        List<IMemberFunction> fuzzySetValues = fuzzySet.getMemberFunctions();
       
        Double crispValue = 0.0;
        Double crispValueDenimenator = 0.0;

        for (int i = 0; i < fuzzySetValues.size(); i++) {
          
            IMemberFunction curFuzzySet = fuzzySetValues.get(i);
           
            double centroid = 0.0;
            double membershipValue = getFuzzifyResult(fuzzySet.getFuzzySetName(), curFuzzySet.getName(),
                    fuzzifyResults);

      
            List<Double> points = curFuzzySet.getPoints();
          
            for (Double point : points) {
                centroid += point.doubleValue();
            }
            centroid /= points.size();
         
            crispValue += membershipValue * centroid;
            crispValueDenimenator += membershipValue;

        }

        if(crispValueDenimenator == 0.0) {
            return new Pair("", 0.0);
        }


        double result = crispValue / crispValueDenimenator;
        String setName = getFuzzySetNameOfOutput(fuzzySetValues, result);
        return new Pair(setName, result);
    }

    private String getFuzzySetNameOfOutput(List<IMemberFunction> fuzzySetValues, Double value) {
        Double mx = 0.0;
        String setName = "";
        for(IMemberFunction mf : fuzzySetValues) {
            Double curVal = mf.getMembershipValue(value);
            if(curVal > mx) {
                mx = curVal;
                setName = mf.getName();
            }
        }

        return setName;
    }

}
