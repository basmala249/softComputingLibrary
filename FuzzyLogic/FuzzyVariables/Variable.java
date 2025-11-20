package FuzzyVariables;

import FuzzySet.FuzzySet;

public class Variable{

    private FuzzySet fuzzySet;
    String name;
    public String getName() {
        return name;
    }
    public  Variable(String name, FuzzySet fuzzySet) {
        this.name = name;
        this.fuzzySet = fuzzySet;
    }
    
    public FuzzySet getFuzzySet() {
        return fuzzySet;
    }
}
