package Defuzzification;

import java.util.List;

import MemberFunction.IMemberFunction;

public interface IDefuzzification {
    double defuzzify(List<IMemberFunction> fuzzySets);
    
}
