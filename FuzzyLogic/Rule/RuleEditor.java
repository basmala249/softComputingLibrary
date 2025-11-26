package Rule;

import java.util.ArrayList;
// import Rule.IRule;
import java.util.List;

public class RuleEditor {

    private List<IRule> rules;
   private final RuleStorage storage;

    public RuleEditor() {
       this.storage = null;
        this.rules = new ArrayList<>();
    }
    public RuleEditor(RuleStorage storage) {
        
        this.storage = storage;
        this.rules = storage.loadRules();
    }

    public List<IRule> getAll() {
        return rules;
    }

    public void addRule(IRule rule) {
        rules.add(rule);
        storage.saveRules(rules);
    }

    public void deleteRule(int index) {
        rules.remove(index);
       storage.saveRules(rules);
    }

    public void updateRule(int index, String newCondition, String newConsequence) {
        IRule rule = rules.get(index);
        rule.setCondition(newCondition);
        rule.setConsequence(newConsequence);
        storage.saveRules(rules);
    }

    public void enableRule(int index) {
        rules.get(index).setEnabled();
        storage.saveRules(rules);
    }

    public void disableRule(int index) {
        rules.get(index).setDisabled();
        storage.saveRules(rules);
    }
}
