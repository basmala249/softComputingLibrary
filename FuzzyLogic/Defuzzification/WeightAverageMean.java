package Defuzzification;
import java.util.List;

import GeneticAlgorithm.Utils.Pair;
import MemberFunction.IMemberFunction;
import java.util.Map;
import java.util.Set;

import FuzzySet.FuzzySet;

public class WeightAverageMean<T extends Number> extends IDefuzzification {

    @Override
    public Pair defuzzify(FuzzySet fuzzySet, Map<String, Set<Pair>> fuzzifyResults) {
        // System.out.println("=== DEFUZZIFICATION STARTED ===");
        // System.out.println("FuzzySet: " + fuzzySet.getFuzzySetName());

        List<IMemberFunction> fuzzySetValues = fuzzySet.getMemberFunctions();
        // System.out.println("Number of member functions: " + fuzzySetValues.size());

        Double crispValue = 0.0;
        Double crispValueDenimenator = 0.0;

        for (int i = 0; i < fuzzySetValues.size(); i++) {
           // System.out.println("\n--- Processing Member Function " + (i + 1) + " ---");

            IMemberFunction curFuzzySet = fuzzySetValues.get(i);
           // System.out.println("Member function name: " + curFuzzySet.getName());

            double centroid = 0.0;
            double membershipValue = getFuzzifyResult(fuzzySet.getFuzzySetName(), curFuzzySet.getName(),
                    fuzzifyResults);

          //  System.out.println("Membership value: " + membershipValue);

            List<Double> points = curFuzzySet.getPoints();
           // System.out.println("Number of points: " + points.size());

            // Calculate centroid
            for (Double point : points) {
                centroid += point.doubleValue();
            }
            centroid /= points.size();
           // System.out.println("Calculated centroid: " + centroid);

            crispValue += membershipValue * centroid;
            crispValueDenimenator += membershipValue;

            // System.out.println("Current crispValue: " + crispValue);
            // System.out.println("Current crispValueDenominator: " + crispValueDenimenator);
        }

        // System.out.println("\n=== FINAL CALCULATION ===");
        // System.out.println("Total crispValue: " + crispValue);
        // System.out.println("Total crispValueDenominator: " + crispValueDenimenator);

        double result = crispValue / crispValueDenimenator;
        // System.out.println("Final defuzzified result: " + result);
        // System.out.println("=== DEFUZZIFICATION COMPLETED ===\n");

        return new Pair("", result);
    }

}
