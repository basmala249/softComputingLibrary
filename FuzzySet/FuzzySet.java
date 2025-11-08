package FuzzySet;

import MemberFunction.IMemberFunction;

import java.util.ArrayList;
import java.util.List;
public class FuzzySet<T extends Number> {
    List<IMemberFunction<T>> memberFunctions;
    public FuzzySet(){
        memberFunctions = new ArrayList<>();
    }
    public void addMemberFunction(IMemberFunction<T> function) {
        memberFunctions.add(function);
    }
    public List<IMemberFunction<T>> getMemberFunctions() {
        return memberFunctions;
    }
}
