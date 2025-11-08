package API_Editor;

import java.util.List;

public class Rules {
    
    public List<String> ruleList;

    public List<String> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<String> ruleList) {
        this.ruleList = ruleList;
    }

    public void addRule(String rule) {
        this.ruleList.add(rule);
    }
    
    public void updateRule(int index, String newRule) {
        this.ruleList.set(index, newRule);
    }
}
