package Rule;

public class MamdaniRule implements IRule {
    private boolean enabled;
    String condition;
    String consequence;
    public MamdaniRule(String condition, String consequence) {
        this.condition = condition;
        this.consequence = consequence;
    }
    public String getCondition() {
        return condition;
    }
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
