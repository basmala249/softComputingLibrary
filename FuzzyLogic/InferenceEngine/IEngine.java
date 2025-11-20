package InferenceEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import FuzzySet.FuzzySet;
import GeneticAlgorithm.Utils.Pair;
import MemberFunction.IMemberFunction;
public abstract class IEngine {
    
    Map<String, List<Pair>> fuzzify(List<String> FuzzysetNames , List<Double> inputs , List<FuzzySet<Double>> fs , List<String> Variables) {
        Map<String , List<Pair>> MS = new HashMap<>();
        List<Pair> tempList = new ArrayList<>();
        for(int j = 0; j < fs.size(); j++) {
            for(int i = 0; i < fs.get(j).getMemberFunctions().size(); i++) {
                IMemberFunction<Double> mf = fs.get(j).getMemberFunctions().get(i);
                tempList.add(new Pair(FuzzysetNames.get(i), mf.getMembershipValue(inputs.get(j))));
            }
            MS.put(Variables.get(j), tempList);
            tempList = new ArrayList<>();
        }
        for(String key : MS.keySet()) {
            System.out.println("Variable: " + key);
            for(Pair p : MS.get(key)) {
                System.out.println("Fuzzy Set: " + p.getFirst() + " Membership Value: " + p.getSecond());
            }
        }
        return MS;
    }
}
