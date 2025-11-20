package FuzzySet;

import java.util.ArrayList;
import java.util.List;

import MemberFunction.IMemberFunction;
public class FuzzySet {
    List<IMemberFunction> memberFunctions;
    public FuzzySet(){
        memberFunctions = new ArrayList<>();
    }
    public void addMemberFunction(IMemberFunction function) {
        memberFunctions.add(function);
    }
    public List<IMemberFunction> getMemberFunctions() {
        return memberFunctions;
    }
}
