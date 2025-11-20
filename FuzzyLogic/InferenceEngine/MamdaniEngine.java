package InferenceEngine;

import java.util.List;

import Rule.MamdaniRule;

public class MamdaniEngine extends IEngine {
    List<MamdaniRule> rules;

    public MamdaniEngine(List<MamdaniRule> rules) {
            this.rules = rules;
  }
    
    
}
