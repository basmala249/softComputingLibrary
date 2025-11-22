package problem;

import java.util.ArrayList;

import java.util.List;

import java.util.Objects;


import FuzzySet.FuzzySet;
import InferenceEngine.IEngine;
import InferenceEngine.MamdaniEngine;
import MemberFunction.IMemberFunction;
import MemberFunction.TrapzoidFunction;
import MemberFunction.TriangleFunction;
import Rule.IRule;
import Utils.GetY;

public class Main {

    public static void main(String[] args) {
        List<Double> input = List.of(60.0,25.0);
        GetY getYUtil = new GetY();
        // Variable 1
        String Variable = "dirt" , fuzzySet1 = "small" , fuzzySet2 = "medium" , fuzzySet3 = "large";
        FuzzySet fs = new FuzzySet();
        IMemberFunction mf1 = new TrapzoidFunction(fuzzySet1, List.of(0.0, 0.0, 20.0, 40.0), getYUtil.getY(List.of(0.0, 0.0, 20.0, 40.0)));
        fs.addMemberFunction(mf1);
        IMemberFunction mf2 = new TrapzoidFunction(fuzzySet2, List.of(20.0, 40.0, 60.0, 80.0), getYUtil.getY(List.of(20.0, 40.0, 60.0, 80.0)));
        fs.addMemberFunction(mf2);
        IMemberFunction mf3 = new TrapzoidFunction(fuzzySet3, List.of(60.0, 80.0, 100.0, 100.0), getYUtil.getY(List.of(60.0, 80.0, 100.0, 100.0)));
        fs.addMemberFunction(mf3);



        // Variable 2
        String Variable1 = "fabric" , fuzzySet12 = "soft" , fuzzySet23 = "ordinary" , fuzzySet34 = "stiff";
        FuzzySet fs1 = new FuzzySet();
        IMemberFunction mf12 = new TrapzoidFunction(fuzzySet12, List.of(0.0, 0.0, 20.0, 40.0), getYUtil.getY(List.of(0.0, 0.0, 20.0, 40.0)));
        fs1.addMemberFunction(mf12);
        IMemberFunction mf23 = new TrapzoidFunction(fuzzySet23, List.of(20.0, 40.0, 60.0, 80.0), getYUtil.getY(List.of(20.0, 40.0, 60.0, 80.0)));
        fs1.addMemberFunction(mf23);
        IMemberFunction mf34 = new TrapzoidFunction(fuzzySet34, List.of(60.0, 80.0, 100.0, 100.0), getYUtil.getY(List.of(60.0, 80.0, 100.0, 100.0)));
        fs1.addMemberFunction(mf34);
        
        
        IEngine engine = new MamdaniEngine() ;
        System.out.println("Fuzzification Results:");
        engine.fuzzify(input, List.of(new FuzzyVariables.Variable(Variable, fs,0, 100 ) , new FuzzyVariables.Variable(Variable1, fs1,0, 100)));
        
        IRule rule1 = new Rule.MamdaniRule("(dirt is small & fabric is soft)", "wash time is short");
        IRule rule2 = new Rule.MamdaniRule("(dirt is medium & fabric is ordinary)", "wash time is medium");
        IRule rule5 = new Rule.MamdaniRule("(dirt is large & fabric is not soft)", "wash time is long");
        IRule rule6 = new Rule.MamdaniRule("(dirt is large & fabric is soft)", "wash time is long");
        IRule rule3 = new Rule.MamdaniRule("((dirt is small & fabric is not soft) | (dirt is medium & fabric is soft))", "wash time is short");
        IRule rule4 = new Rule.MamdaniRule("(dirt is medium & fabric is stiff)", "wash time is medium");
        System.out.println("Inference Results:");
        engine.inferRules(List.of(rule1, rule2, rule3, rule4, rule5, rule6));



        // test defuzzification separately
        System.out.println("Defuzzification Results:");
        // output
        String fSet1 = "small" , fSet2 = "standard";
        FuzzySet outputfs = new FuzzySet();
        IMemberFunction small = new TriangleFunction(fSet1, List.of(0.0, 15.0, 30.0), getYUtil.getY(List.of(0.0, 15.0, 30.0)));
        small.setfinalY(0.75);
        outputfs.addMemberFunction(small);
        IMemberFunction standard = new TriangleFunction(fSet2, List.of(15.0, 30.0, 45.0), getYUtil.getY(List.of(15.0, 30.0, 45.0)));
        standard.setfinalY(0.25);
        outputfs.addMemberFunction(standard);

        Defuzzification.MeanMax defuzz = new Defuzzification.MeanMax();
        var result = defuzz.defuzzify(outputfs.getMemberFunctions());
        System.out.println("Defuzzified Output: Set Name = " + result.getFirst() + ", Value = " + result.getSecond());



    }
}
