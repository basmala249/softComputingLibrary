package InferenceEngine;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import GeneticAlgorithm.Utils.Pair;

public class SugenoEngine extends IEngine {
    Double numerator = 0.0, denominator = 0.0;

    @Override
    public void setConsequenceValue(String consequence, Double value, Map<String, Double> variables) {
        // System.out.println("///////////////////");
        // System.err.println(variables);
        // System.out.println("///////////////////");

        ///
        String expr = consequence.split("=")[1].trim();

        Expression expression = new ExpressionBuilder(expr)
                .variables(variables.keySet())
                .build()
                .setVariables(variables);

        double val = expression.evaluate();

        numerator += val * value;
        denominator += value;
    }

    @Override 
    public Map<String, Set<Pair>> runConsequences(){
        Map<String, Set<Pair>> res = new HashMap<>();
        if (denominator == 0) {
            System.out.println("The denominator is 0!");
            return res;
        }
        System.out.println("//////////////////////////////");
        System.out.println(numerator / denominator);
        System.out.println("//////////////////////////////");

        return new HashMap<>();
    
    }
    
}
