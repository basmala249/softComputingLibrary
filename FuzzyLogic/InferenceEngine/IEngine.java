package InferenceEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import FuzzySet.FuzzySet;
import FuzzyVariables.Variable;
import GeneticAlgorithm.Utils.Pair;
import MemberFunction.IMemberFunction;
import RecursiveRule.interpretRule;
import Rule.IRule;
public abstract class IEngine {
    public interpretRule interpretRule = null;
    private Map<String , Set<Pair>> newMap = new HashMap<>();
    Map<String, Set<Pair>> fuzzify(List<Double> inputs , List<FuzzySet> fs , List<Variable> Variables) {
        Map<String , Set<Pair>> MS = new HashMap<>();
        List<Pair> tempList = new ArrayList<>();
        for(int j = 0; j < fs.size(); j++) {
            for(int i = 0; i < fs.get(j).getMemberFunctions().size(); i++) {
                IMemberFunction mf = fs.get(j).getMemberFunctions().get(i);
                tempList.add(new Pair(mf.getName(), mf.getMembershipValue(inputs.get(j))));
            }
            MS.put(Variables.get(j).getName(), new HashSet<>(tempList));
            tempList = new ArrayList<>();
        }
        newMap = MS;
        return MS;
    }
    Map<String, Set<Pair>> inferRules(List<IRule> rules) {
        interpretRule = new interpretRule(newMap);
        Map<String, Set<Pair>> MS = new HashMap<>();
        for(IRule rule : rules) {
            if(rule.isEnabled()) {
                Double conditionValue = extractCondition(rule.getCondition());
                setConsequenceValue(conditionValue);
            }
           
        }
        return MS;
        
    }
    Double extractCondition(String rule ) {
        return interpretRule.evaluteRule(rule);
    }

    Map<String, Set<Pair>> getNewMap() {
        return newMap;
    }
    void setConsequenceValue(Double value){

    }

}
