package Rule;

public class SurgenoRule implements IRule {
    private boolean enabled;
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
