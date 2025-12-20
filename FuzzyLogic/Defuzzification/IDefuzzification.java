package FuzzyLogic.Defuzzification;

import GeneticAlgorithm.Utils.Pair;
import java.util.Map;
import java.util.Set;

import FuzzyLogic.FuzzySet.FuzzySet;

public abstract class IDefuzzification {
    public abstract Pair defuzzify(FuzzySet fuzzySets, Map<String, Set<Pair>> fuzzifyResults);

    protected Double getFuzzifyResult(String varName, String valueName, Map<String, Set<Pair>> fuzzifyResults) {
        Set<Pair> variablesValues = fuzzifyResults.get(varName);
        if (variablesValues != null) {
            for (Pair p : variablesValues) {
                if (p.getFirst().equals(valueName)) {
                    return (Double) p.getSecond();
                }
            }
        }
        return 0.0;
    }
    
}
