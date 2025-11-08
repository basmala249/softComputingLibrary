package Rule;

public class MamdaniRule implements IRule {
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
