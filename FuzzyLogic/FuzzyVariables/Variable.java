package FuzzyVariables;

import FuzzySet.FuzzySet;

public class Variable{

    private FuzzySet fuzzySet;
    String name;
    private double lb;
    private double ub;
    
    public String getName() {
        return name;
    }
    public  Variable(String name, FuzzySet fuzzySet,double lb,double u) {
        this.name = name;
        this.fuzzySet = fuzzySet;
        this.lb=lb;
        this.ub = ub;
    }
    
    public FuzzySet getFuzzySet() {
        return fuzzySet;
    }

     public getLowerBound(){return lb;}
    public getUpperBound(){return ub;}
}
