package FuzzyLogic.Defuzzification;

import java.util.List;

import GeneticAlgorithm.Utils.Pair;

import FuzzyLogic.MemberFunction.IMemberFunction;
import FuzzyLogic.Shape.IShape;
import java.util.Set;
import java.util.Map;

import FuzzyLogic.FuzzySet.FuzzySet;   


public class MeanMax extends IDefuzzification {
	
    @Override
    public Pair defuzzify(FuzzySet parentFuzzySet, Map<String, Set<Pair>> fuzzifyResults) {
        
        List<IMemberFunction> maxSets = getMaxMembership(parentFuzzySet, fuzzifyResults);
        if(maxSets == null || maxSets.isEmpty()) {
            System.err.println("No fuzzy sets with maximum membership found.");
            return new Pair("", 0.0);
        }
        double sum = 0.0;
        int count = 0;
        for(IMemberFunction mf : maxSets) {
            if(mf != null) {
                List<IShape> equations = mf.getEquations();
                
                Double membershipValue = getFuzzifyResult(parentFuzzySet.getFuzzySetName(), mf.getName(), fuzzifyResults);

                List<Double> xValues = getXValues(equations, membershipValue);
                if(xValues != null && !xValues.isEmpty()) {
                    for(Double x : xValues) {
                        double xValue = x.doubleValue();
                        sum += xValue;
                        count++;
                    }
                }
            }
        }
        //String setName = maxSets.get((int)(maxSets.size()/2)).getName();
        List<IMemberFunction> fuzzySetValues = parentFuzzySet.getMemberFunctions();
        String setName = getFuzzySetNameOfOutput(fuzzySetValues, sum / count);
        return count == 0 ? new Pair("", 0.0) : new Pair(setName, sum / count);
    }

    private List<IMemberFunction> getMaxMembership(FuzzySet parentFuzzySet, Map<String, Set<Pair>> fuzzifyResults) {
        List<IMemberFunction> maxSets = new java.util.ArrayList<>();
        List<IMemberFunction> fuzzySets = parentFuzzySet.getMemberFunctions();
        if (fuzzySets == null) {
            System.err.println("Invalid input: fuzzySets must be non-null.");
            return maxSets;
        }
        double maxMembership = 0.0;
        for (IMemberFunction mf : fuzzySets)
           
            if (mf != null) {

                double curMembership = getFuzzifyResult(parentFuzzySet.getFuzzySetName(), mf.getName(), fuzzifyResults);

                maxMembership = Math.max(maxMembership, curMembership);

            }

        for (int i = 0; i < fuzzySets.size(); i++) {
            Double v = getFuzzifyResult(parentFuzzySet.getFuzzySetName(), fuzzySets.get(i).getName(), fuzzifyResults);
            if (v != null &&  v.doubleValue() ==  maxMembership) {
                maxSets.add(fuzzySets.get(i));
            }
        }
        return maxSets;
    }

    private List<Double> getXValues ( List<IShape> equations, Double y) {
        List<Double> xValues = new java.util.ArrayList<>();
        for (int i = 0; i < equations.size(); i++) {
            IShape line = equations.get(i);
            double slope = line.getSlope();
            if(slope == 0) continue; 
            else if(slope == Double.POSITIVE_INFINITY) {
                double x = line.getIntercept();
                xValues.add(x);
                continue;
            }
            double intercept = line.getIntercept();
            double x = (y - intercept) / slope;
            xValues.add(x);
        }
        return xValues;
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
