package InferenceEngine;

import java.util.List;

import Rule.SugenoRule;
public class SugenoEngine extends IEngine {
    List<SugenoRule> rules;

    public SugenoEngine(List<SugenoRule> rules) {
        this.rules = rules;
    }
    
    
}
