package InferenceEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import FuzzyVariables.Variable;
import GeneticAlgorithm.Utils.Pair;
import MemberFunction.IMemberFunction;
import RecursiveRule.interpretRule;
import Rule.IRule;
public abstract class IEngine {
    public interpretRule interpretRule = null;
    private Map<String , Set<Pair>> newMap = new HashMap<>();
    public Map<String, Set<Pair>> fuzzify(List<Double> inputs ,  List<Variable> Variables) {
        Map<String , Set<Pair>> MS = new HashMap<>();
        List<Pair> tempList = new ArrayList<>();
        for(int j = 0; j < Variables.size(); j++) {
            for(int i = 0; i < Variables.get(j).getFuzzySet().getMemberFunctions().size(); i++) {
                IMemberFunction mf = Variables.get(j).getFuzzySet().getMemberFunctions().get(i);
                tempList.add(new Pair(mf.getName(), mf.getMembershipValue(inputs.get(j))));
            }
            MS.put(Variables.get(j).getName(), new HashSet<>(tempList));
            tempList = new ArrayList<>();
        }
        newMap = MS;
        for (Map.Entry<String, Set<Pair>> entry : newMap.entrySet()) {
            String key = entry.getKey();
            Set<Pair> valueSet = entry.getValue();
            System.out.print(key + ": ");
            for (Pair pair : valueSet) {
                System.out.print("[" + pair.getFirst() + ", " + pair.getSecond() + "] ");
            }
            System.out.println();
        }
        return MS;
    }
    public Map<String, Set<Pair>> inferRules(List<IRule> rules) {
        interpretRule = new interpretRule(newMap);
        Map<String, Set<Pair>> MS = new HashMap<>();
        for(IRule rule : rules) {
            if(rule.isEnabled()) {
                Double conditionValue = extractCondition(rule.getCondition());
                System.out.println("Condition Value: " + conditionValue);
                //setConsequenceValue(rule.getConsequence(), conditionValue);
            }
           
        }
        return MS;
        
    }
    public Double extractCondition(String rule ) {
        System.out.println("Evaluating Rule: " + rule);
        return interpretRule.evaluteRule(rule);
    }

    public Map<String, Set<Pair>> getNewMap() {
        return newMap;
    }
    public void setConsequenceValue(String Consquence , Double value){

    }

}
