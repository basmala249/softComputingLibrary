package FuzzyVariables;

import FuzzySet.FuzzySet;

public class Variable<T extends Number> {

    private FuzzySet<T> fuzzySet;
    Variable(FuzzySet<T> fuzzySet) {
        this.fuzzySet = fuzzySet;
    }

    public FuzzySet<T> getFuzzySet() {
        return fuzzySet;
    }
}
