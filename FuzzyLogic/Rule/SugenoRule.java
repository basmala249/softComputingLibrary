package FuzzyLogic.Rule;

public class SugenoRule implements IRule {
    String condition;
    String consequence;
    public SugenoRule(String condition, String consequence) {
        this.condition = condition;
        this.consequence = consequence;
    }
    @Override
    public String getCondition() {
        return condition;
    }
    @Override
    public String getConsequence() {
        return consequence;
    }
    @Override
    public void setCondition(String condition) {
        this.condition = condition;
    }
    @Override
    public void setConsequence(String consequence) {
        this.consequence = consequence;
    }
    private boolean enabled = true;
    @Override
    public boolean isEnabled() {
        return enabled;
    }
    @Override
    public void setEnabled() {
        this.enabled = true;
    }   
    @Override
    public void setDisabled() {
        this.enabled = false;
    }
}
