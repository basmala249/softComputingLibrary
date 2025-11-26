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
    String var2BePredicted;
    public interpretRule interpretRule = null;
    public Map<String , Set<Pair>> mainMap = new HashMap<>();
    
    public Map<String, Set<Pair>> fuzzify(List<Double> inputs ,  List<Variable> Variables) {

        Map<String , Set<Pair>> MS = new HashMap<>();
        List<Pair> tempList = new ArrayList<>();
        for(int j = 0; j < Variables.size(); j++) {
            System.out.println("Variable: " + Variables.get(j).getName());
            for(int i = 0; i < Variables.get(j).getFuzzySet().getMemberFunctions().size(); i++) {
                IMemberFunction mf = Variables.get(j).getFuzzySet().getMemberFunctions().get(i);
    
                tempList.add(new Pair(mf.getName(), mf.getMembershipValue(inputs.get(j))));
            }
            MS.put(Variables.get(j).getName(), new HashSet<>(tempList));
            tempList = new ArrayList<>();
        }
        mainMap = MS;
        // for (Map.Entry<String, Set<Pair>> entry : mainMap.entrySet()) {
        //     String key = entry.getKey();
        //     Set<Pair> valueSet = entry.getValue();
        //     System.out.print(key + ": ");
        //     for (Pair pair : valueSet) {
        //         System.out.print("[" + pair.getFirst() + ", " + pair.getSecond() + "] ");
        //     }
        //     System.out.println();
        // }

        return MS;
    }


    public Map<String, Set<Pair>> inferRules(List<IRule> rules, Map<String, Double> variables) {
        interpretRule = new interpretRule(mainMap);
        
    
        for(IRule rule : rules) {/// (dirt is small & fabric is soft)", "wash time is short"
            if(rule.isEnabled()) {
                Double conditionValue = extractCondition(rule.getCondition());
                System.out.println("Evaluated Condition for Rule IF " + rule.getCondition() + " THEN " + rule.getConsequence());
                System.out.println("Condition Value: " + conditionValue);
                setConsequenceValue(rule.getConsequence(), conditionValue, variables);
            }
        }
        return runConsequences();
    }

    public Double extractCondition(String rule ) {
        // System.out.println("Evaluating Rule: " + rule);
        return interpretRule.evaluteRule(rule);
    }

    public Map<String, Set<Pair>> getMainMap() {
        return mainMap;
    }
    public void setConsequenceValue(String Consquence , Double value, Map<String, Double> variables){

    }

    public abstract Map<String, Set<Pair>> runConsequences();

}
