package InferenceEngine;

import java.util.List;

import Rule.MamdaniRule;

public class MamdaniEngine implements IEngine {
    List<MamdaniRule> rules;

    public MamdaniEngine(List<MamdaniRule> rules) {
            this.rules = rules;
 }
    @Override
    public void infer() {
        // Implementation of Mamdani inference
    }
    
}
