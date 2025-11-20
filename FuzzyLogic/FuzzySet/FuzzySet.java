package FuzzySet;

import java.util.ArrayList;
import java.util.List;

import MemberFunction.IMemberFunction;
public class FuzzySet {
    List<IMemberFunction> memberFunctions;
    String name;
    public FuzzySet(String name){
        this.name = name;
        memberFunctions = new ArrayList<>();
    }
    public void addMemberFunction(IMemberFunction function) {
        memberFunctions.add(function);
    }
    public List<IMemberFunction> getMemberFunctions() {
        return memberFunctions;
    }
}
