package InferenceEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import FuzzySet.FuzzySet;
import FuzzyVariables.Variable;
import GeneticAlgorithm.Utils.Pair;
import MemberFunction.IMemberFunction;
import Rule.IRule;
public abstract class IEngine {
    
    
    Map<String, List<Pair>> fuzzify(List<Double> inputs , List<FuzzySet> fs , List<Variable> Variables) {
        Map<String , List<Pair>> MS = new HashMap<>();
        List<Pair> tempList = new ArrayList<>();
        for(int j = 0; j < fs.size(); j++) {
            for(int i = 0; i < fs.get(j).getMemberFunctions().size(); i++) {
                IMemberFunction mf = fs.get(j).getMemberFunctions().get(i);
                tempList.add(new Pair(mf.getName(), mf.getMembershipValue(inputs.get(j))));
            }
            MS.put(Variables.get(j).getName(), tempList);
            tempList = new ArrayList<>();
        }
        return MS;
    }
    void inferRules(List<IRule> rules) {
        Map<String, List<Pair>> MS = new HashMap<>();
        for(IRule rule : rules) {
            if(rule.isEnabled()) {
                Double conditionValue = extractCondition(rule.getCondition());
                //setConsequenceValue(conditionValue);
            }
           
        }
        
    }
    Double extractCondition(String rule) {
        return 0.0;
    }
    //void setConsequenceValue(Double value);

}
