package API_Editor;


public class Rules {
    String condition;
    String consequence;
    public Rules(String condition, String consequence) {
        this.condition = condition;
        this.consequence = consequence;
    }
    public String getCondition() {
        return condition;
    }
    public String getConsequence() {
        return consequence;
    }
    void setCondition(String condition) {
        this.condition = condition;
    }
    void setConsequence(String consequence) {
        this.consequence = consequence;
    }
}
