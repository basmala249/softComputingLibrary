package InferenceEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import GeneticAlgorithm.Utils.Pair;

public class SugenoEngine extends IEngine {
    private ScriptEngine engine;
    Double numerator = 0.0, denominator = 0.0;

    public SugenoEngine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByName("Nashorn"); // JS engine
    }

    @Override
    public void setConsequenceValue(String consequence, Double value, Map<String, Double> variables) {
        try {
            // Make sure engine is accessible here
            for (Map.Entry<String, Double> entry : variables.entrySet()) {
                engine.put(entry.getKey(), entry.getValue());
            }

            // Remove "y1 = " part
            String expr = consequence.split("=")[1].trim();

            Object result = engine.eval(expr);
            double val = Double.parseDouble(result.toString());

            numerator += val * value;
            denominator += value;

        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Override 
    public Map<String, Set<Pair>> runConsequences(){
        Map<String, Set<Pair>> res = new HashMap<>();
        if (denominator == 0)  return res;
        System.out.println("//////////////////////////////");
        System.out.println(numerator / denominator);
        System.out.println("//////////////////////////////");

        return new HashMap<>();
    
    }
    
}
