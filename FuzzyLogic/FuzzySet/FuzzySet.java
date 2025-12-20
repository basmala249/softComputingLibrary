package FuzzyLogic.FuzzySet;

import java.util.ArrayList;
import java.util.List;

import FuzzyLogic.MemberFunction.IMemberFunction;
public class FuzzySet {
    private String fuzzySetName;
    List<IMemberFunction> memberFunctions;
    public FuzzySet() {
        memberFunctions = new ArrayList<>();
    }

    public FuzzySet(String fuzzySetName) {
        this.fuzzySetName = fuzzySetName;
        memberFunctions = new ArrayList<>();
    }
    public void addMemberFunction(IMemberFunction function) {
        memberFunctions.add(function);
    }
    public List<IMemberFunction> getMemberFunctions() {
        return memberFunctions;
    }
    public String getFuzzySetName() {
        return fuzzySetName;
    }
}
