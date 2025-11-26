package Defuzzification;

import java.util.List;

import GeneticAlgorithm.Utils.Pair;

import MemberFunction.IMemberFunction;
import Shape.IShape;
import java.util.Set;

import FuzzySet.FuzzySet;   


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
                        sum += x.doubleValue();
                        count++;
                    }
                }
            }
        }
        String setName = maxSets.get((int)(maxSets.size()/2)).getName();
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
            Double v = fuzzySets.get(i).getMembership();
            if (v != null &&  v ==  maxMembership) {
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
            double intercept = line.getIntercept();
            double x = (y - intercept) / slope;
            xValues.add(x);
        }
        return xValues;
    }

    
}
