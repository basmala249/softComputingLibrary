package problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import FuzzySet.FuzzySet;
import InferenceEngine.IEngine;
import InferenceEngine.MamdaniEngine;
import InferenceEngine.SugenoEngine;
import MemberFunction.IMemberFunction;
import MemberFunction.TrapzoidFunction;
import MemberFunction.TriangleFunction;
import Rule.*;
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
        
        
        // IEngine engine = new MamdaniEngine() ;
        // System.out.println("Fuzzification Results:");
        // engine.fuzzify(input, List.of(new FuzzyVariables.Variable(Variable, fs,0, 100 ) , new FuzzyVariables.Variable(Variable1, fs1,0, 100)));
        // System.out.println("Fuzzification Done:");

        // RuleStorage storage =new RuleStorage("rules.json");
        // RuleEditor editor =new RuleEditor(storage);
        // if (editor.getAll().isEmpty()) {
        //     System.out.println("No rules found → creating initial rules...");

        //     editor.addRule(new MamdaniRule("(dirt is small & fabric is soft)", "wash time is short"));
        //     editor.addRule(new MamdaniRule("(dirt is medium & fabric is ordinary)", "wash time is medium"));
        //     editor.addRule(new MamdaniRule("(dirt is large & fabric is not soft)", "wash time is long"));
        //     editor.addRule(new MamdaniRule("(dirt is large & fabric is soft)", "wash time is long"));
        //     editor.addRule(new MamdaniRule("((dirt is small & fabric is not soft) | (dirt is medium & fabric is soft))", "wash time is short"));
        //     editor.addRule(new MamdaniRule("(dirt is medium & fabric is stiff)", "wash time is medium"));

        //     System.out.println("Initial rules created and saved to rules.json\n");
        // }

        // List<IRule> rules = editor.getAll();
        
        
        // engine.inferRules(rules);


        IEngine engine = new SugenoEngine() ;
        System.out.println("Fuzzification Results:");
        engine.fuzzify(input, List.of(new FuzzyVariables.Variable(Variable, fs,0, 100 ) , new FuzzyVariables.Variable(Variable1, fs1,0, 100)));
        System.out.println("Fuzzification Done:");

        RuleStorage storage =new RuleStorage("rules.json");
        RuleEditor editor =new RuleEditor(storage);
        if (editor.getAll().isEmpty()) {
            System.out.println("No rules found → creating initial rules...");

            editor.addRule(new SugenoRule("(dirt is small & fabric is soft)", "y1 = (-x1) + x2 + 1"));
            editor.addRule(new SugenoRule("(dirt is medium & fabric is ordinary)", "y2 = (-x2) + 3"));
            editor.addRule(new SugenoRule("(dirt is large & fabric is not soft)", "y3 = (-x1) + 3"));
            editor.addRule(new SugenoRule("(dirt is large & fabric is soft)", "y4 = (-x1) + x2 + 2"));
            editor.addRule(new SugenoRule("(dirt is medium & fabric is stiff)", "y5 = 2*x1 + (-x2) + 1"));

            System.out.println("Initial rules created and saved to rules.json\n");
        }

        List<IRule> rules = editor.getAll();
        
        Map<String, Double> variables = new HashMap<>();
        variables.put("X1", 1.5);
        variables.put("X2", 2.5);
        
        engine.inferRules(rules, variables);



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
