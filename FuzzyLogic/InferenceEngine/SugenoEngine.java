package InferenceEngine;

import java.util.List;

import Rule.SugenoRule;
public class SugenoEngine implements IEngine {
    List<SugenoRule> rules;

    public SugenoEngine(List<SugenoRule> rules) {
        this.rules = rules;
    }
    @Override
    public void infer() {
        // Implementation of Sugeno inference
    }
    
}
