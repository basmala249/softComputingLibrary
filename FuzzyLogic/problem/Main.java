package problem;


import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.Set;

import Defuzzification.IDefuzzification;
import Defuzzification.MeanMax;
import FuzzySet.FuzzySet;
import GeneticAlgorithm.Utils.Pair;
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
    
        //List<Double> inputLab = List.of(100.0,25.0);
        List<Double> inputLab = new ArrayList<>( List.of(100.0,25.0));


        // Variable 1
        String Variable = "dirt" , fuzzySet1 = "small" , fuzzySet2 = "medium" , fuzzySet3 = "large";
        FuzzySet fs = new FuzzySet();
        GetY getYUtil = new GetY();
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
        
        
        IEngine engineLab = new MamdaniEngine() ;
                
        RuleStorage storageLab =new RuleStorage("rules.json");
       // RuleStorage storageLab =new RuleStorage("rules.json");
        RuleEditor editorLab =new RuleEditor(storageLab);
        List<IRule> rulesLab  = editorLab.getAll();

        if (rulesLab.isEmpty()) {
            System.out.println("Error Reading Json File\n");
            return;
        }
        for(IRule r : rulesLab){
            System.out.println("Rule Condition: " + r.getCondition() + " => Consequence: " + r.getConsequence());
        }



        List<FuzzyVariables.Variable> Variables = List.of(
                new FuzzyVariables.Variable(Variable, fs,0, 100 ) ,
                new FuzzyVariables.Variable(Variable1, fs1,1, 10)
        );
        for(int j = 0 ; j < inputLab.size();j++){
            double lb = Variables.get(j).getLowerBound();
            double ub = Variables.get(j).getUpperBound();
            if(inputLab.get(j) == null || Double.isNaN(inputLab.get(j))){
                inputLab.set(j,(lb+ub)/2);
                System.out.println("input is null new default = " +(lb+ub)/2);
            }
            else if (inputLab.get(j) < lb) {
                inputLab.set(j,lb);
                System.out.println("input is out of range new default = " +lb);

            }else if (inputLab.get(j) > ub){
                System.out.println("input is out of range new default = " +ub);
                inputLab.set(j,ub-1);
            }
        }
        
        

        engineLab.fuzzify(inputLab, 
            List.of(new FuzzyVariables.Variable(Variable, fs,0, 100 ) , 
                    new FuzzyVariables.Variable(Variable1, fs1,1, 10))
        );
    
    
        System.out.println("Inference Results:");
        Map<String, Set<Pair>> mpLab = engineLab.inferRules(rulesLab, null);

        for (Map.Entry<String, Set<Pair>> entry : mpLab.entrySet()) {
            String key = entry.getKey();
            Set<Pair> valueSet = entry.getValue();
            System.out.print(key + ": ");
            for (Pair pair : valueSet) {
                System.out.print("[" + pair.getFirst() + ", " + pair.getSecond() + "] ");
            }
            System.out.println();
        }

        
         // output
        String washTime = "wash time";
        String fSet1 = "small" , fSet2 = "standard", fSet3 = "large", fSet4 = "veryLarge", fSet5 = "verySmall";
        FuzzySet outputfs = new FuzzySet(washTime);
        IMemberFunction small = new TriangleFunction(fSet1, List.of(0.0, 15.0, 30.0), getYUtil.getY(List.of(0.0, 15.0, 30.0)));
        outputfs.addMemberFunction(small);
        IMemberFunction standard = new TriangleFunction(fSet2, List.of(15.0, 30.0, 45.0), getYUtil.getY(List.of(15.0, 30.0, 45.0)));
        outputfs.addMemberFunction(standard);
        IMemberFunction large = new TriangleFunction(fSet3, List.of(30.0, 45.0, 60.0), getYUtil.getY(List.of(30.0, 45.0, 60.0)));
        outputfs.addMemberFunction(large);
        IMemberFunction veryLarge = new TriangleFunction(fSet4, List.of(45.0, 60.0, 60.0), getYUtil.getY(List.of(45.0, 60.0, 60.0)));
        outputfs.addMemberFunction(veryLarge);
        IMemberFunction verySmall = new TriangleFunction(fSet5, List.of(0.0, 0.0, 15.0), getYUtil.getY(List.of(0.0, 0.0, 15.0)));
        outputfs.addMemberFunction(verySmall);
        
        
        IDefuzzification defuzzLabAvg = new Defuzzification.WeightAverageMean<>();
        IDefuzzification defuzzLabMax = new MeanMax();


        Pair resultLab = defuzzLabAvg.defuzzify(outputfs, mpLab);
        System.out.println("Defuzzified Weight Average Mean Output: Set Name = " + resultLab.getFirst() + ", Value = " + resultLab.getSecond());
        Pair resultLabMax = defuzzLabMax.defuzzify(outputfs, mpLab);
        System.out.println("Defuzzified Mean Max Output: Set Name = " + resultLabMax.getFirst() + ", Value = " + resultLabMax.getSecond());

    }
}


