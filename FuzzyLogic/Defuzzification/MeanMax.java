package Defuzzification;

import java.util.List;
import java.util.Map;

import MemberFunction.IMemberFunction;
import Shape.IShape;

public class MeanMax implements IDefuzzification {
	@Override
    public double defuzzify(List<IMemberFunction> fuzzySets) {
        List<IMemberFunction> maxSets = getMaxMembership(fuzzySets);
        if(maxSets == null || maxSets.isEmpty()) {
            System.err.println("No fuzzy sets with maximum membership found.");
            return 0.0;
        }
        double sum = 0.0;
        int count = 0;
        for(IMemberFunction mf : maxSets) {
            if(mf != null) {
                List<IShape> equations = mf.getEquations();
                Double membershipValue = mf.getMembership();
                List<Double> xValues = getXValues(equations, membershipValue);
                if(xValues != null && !xValues.isEmpty()) {
                    for(Double x : xValues) {
                        sum += x.doubleValue();
                        count++;
                    }
                }
            }
        }
        return count == 0 ? 0.0 : sum / count;
    }

    private List<IMemberFunction> getMaxMembership(List<IMemberFunction> fuzzySets) {
        List<IMemberFunction> maxSets = new java.util.ArrayList<>();
        if (fuzzySets == null) {
            System.err.println("Invalid input: fuzzySets must be non-null.");
            return maxSets;
        }
        double maxMembership = 0.0;
        for (IMemberFunction mf : fuzzySets)
            if (mf != null)  maxMembership = Math.max(maxMembership, mf.getMembership());

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
            double intercept = line.getIntercept();
            double x = (y - intercept) / slope;
            xValues.add(x);
        }
        return xValues;
    }
}
