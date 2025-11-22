package Defuzzification;

import java.util.List;

import GeneticAlgorithm.Utils.Pair;
import MemberFunction.IMemberFunction;

public interface IDefuzzification {
    Pair defuzzify(List<IMemberFunction> fuzzySets);
    
}
