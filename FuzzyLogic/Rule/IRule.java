package Rule;

public interface IRule {
    boolean isEnabled();
    void setEnabled();
    void setDisabled();
    void setCondition(String condition);
    void setConsequence(String consequence);
    String getCondition();
    String getConsequence();
}
