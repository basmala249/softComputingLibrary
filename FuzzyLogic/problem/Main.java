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
        List<Double> input = List.of(75.0,6.0,8.0);
        GetY getYUtil = new GetY();
        String studyPreparationVar = "Study_Preparation"
         , fuzzySetSP1 = "Poor" , fuzzySetSP2 = "Average" , fuzzySetSP3 = "Excellent";

        FuzzySet sp_fs = new FuzzySet(studyPreparationVar);

        IMemberFunction sp_mf1 = new TrapzoidFunction(fuzzySetSP1, List.of(0.0, 0.0, 30.0, 50.0), getYUtil.getY(List.of(0.0, 0.0, 30.0, 50.0)));
        sp_fs.addMemberFunction(sp_mf1);
        IMemberFunction sp_mf2 = new TriangleFunction(fuzzySetSP2, List.of(40.0, 60.0, 80.0), getYUtil.getY(List.of(40.0, 60.0, 80.0)));
        sp_fs.addMemberFunction(sp_mf2);
        IMemberFunction sp_mf3 = new TrapzoidFunction(fuzzySetSP3, List.of(70.0, 85.0, 100.0, 100.0), getYUtil.getY(List.of(70.0, 85.0, 100.0, 100.0)));
        sp_fs.addMemberFunction(sp_mf3);

        //Subject Difficulty
        String subjectDifficultyVar = "Subject_Difficulty"
         , fuzzySetSD1 = "Easy" , fuzzySetSD2 = "Moderate" , fuzzySetSD3 = "Hard";
        FuzzySet sd_fs = new FuzzySet(subjectDifficultyVar);


        //IMemberFunction sd_mf1 = new TrapzoidFunction(fuzzySetSD1, List.of(1.0,1.0,3.0,5.0), getYUtil.getY(List.of(0.0, 0.0, 30.0, 50.0)));
        IMemberFunction sd_mf1 = new TrapzoidFunction(fuzzySetSD1, List.of(1.0,1.0,3.0,5.0), getYUtil.getY(List.of(1.0,1.0,3.0,5.0)));

       
        sd_fs.addMemberFunction(sd_mf1);

        IMemberFunction sd_mf2 = new TriangleFunction(fuzzySetSD2, List.of(4.0, 6.0, 8.0), getYUtil.getY(List.of(4.0, 6.0, 8.0)));
        sd_fs.addMemberFunction(sd_mf2);

        IMemberFunction sd_mf3 = new TrapzoidFunction(fuzzySetSD3, List.of(7.0, 9.0, 10.0, 10.0), getYUtil.getY(List.of(7.0, 9.0, 10.0, 10.0)));
        sd_fs.addMemberFunction(sd_mf3);



        // Sleep Quality
        String sleepQualityVar = "Sleep_Quality"
         , fuzzySetSQ1 = "Poor" , fuzzySetSQ2 = "Adequate" , fuzzySetSQ3 = "Excellent";

        FuzzySet sq_fs = new FuzzySet(sleepQualityVar);
        IMemberFunction sq_mf1 = new TrapzoidFunction(fuzzySetSQ1, List.of(0.0,0.0,3.0,5.0), getYUtil.getY(List.of(0.0,0.0,3.0,5.0)));
        sq_fs.addMemberFunction(sq_mf1);
        IMemberFunction sq_mf2 = new TriangleFunction(fuzzySetSQ2, List .of(4.0,6.0,8.0), getYUtil.getY(List.of(4.0,6.0,8.0)));
        sq_fs.addMemberFunction(sq_mf2);        
        IMemberFunction sq_mf3 = new TrapzoidFunction(fuzzySetSQ3, List.of(7.0,9.0,10.0,10.0), getYUtil.getY(List.of(7.0,9.0,10.0,10.0)));
        sq_fs.addMemberFunction(sq_mf3);


      
        // outputs stress level
        String stressLevelVar = "Stress_Level"
         , fuzzySetSL1 = "Low" , fuzzySetSL2 = "Medium" , fuzzySetSL3 = "High";
        FuzzySet sl_fs = new FuzzySet(stressLevelVar);
        IMemberFunction sl_mf1 = new TrapzoidFunction(fuzzySetSL1, List.of(0.0,0.0,25.0,45.0), getYUtil.getY(List.of(0.0,0.0,25.0,45.0)));
        sl_fs.addMemberFunction(sl_mf1);
        IMemberFunction sl_mf2 = new TriangleFunction(fuzzySetSL2, List.of(30.0,50.0,70.0), getYUtil.getY(List.of(30.0,50.0,70.0)));
        sl_fs.addMemberFunction(sl_mf2);
        IMemberFunction sl_mf3 = new TrapzoidFunction(fuzzySetSL3, List.of(55.0,75.0,100.0,100.0), getYUtil.getY(List.of(55.0,75.0,100.0,100.0)));
        sl_fs.addMemberFunction(sl_mf3);



        
        IEngine engine = new MamdaniEngine() ;
                
        RuleStorage storage =new RuleStorage("D:\\study\\SC\\SoftComputingTool\\softComputingLibrary\\mamdani.json");
        RuleEditor editor =new RuleEditor(storage);
        List<IRule> rules  = editor.getAll();

        if (rules.isEmpty()) {
            System.out.println("Error Reading Json File\n");
            return;
        }
        for(IRule r : rules){
            System.out.println("Rule Condition: " + r.getCondition() + " => Consequence: " + r.getConsequence());
        }

            
        

        engine.fuzzify(input, 
            List.of(new FuzzyVariables.Variable(studyPreparationVar, sp_fs,0, 100 ) , 
                    new FuzzyVariables.Variable(subjectDifficultyVar, sd_fs,1, 10),
                    new FuzzyVariables.Variable(sleepQualityVar, sq_fs,0, 10))
        );
    
    
        System.out.println("Inference Results:");
        Map<String, Set<Pair>> mp = engine.inferRules(rules, null);

        for (Map.Entry<String, Set<Pair>> entry : mp.entrySet()) {
            String key = entry.getKey();
            Set<Pair> valueSet = entry.getValue();
            System.out.print(key + ": ");
            for (Pair pair : valueSet) {
                System.out.print("[" + pair.getFirst() + ", " + pair.getSecond() + "] ");
            }
            System.out.println();
        }

     
        
        IDefuzzification defuzzAvg = new Defuzzification.WeightAverageMean<>();
        IDefuzzification defuzzMax = new MeanMax();


        Pair result = defuzzMax.defuzzify(sl_fs, mp);

        Pair weighted_output = defuzzAvg.defuzzify(sl_fs, mp);

        System.out.println("\nDefuzzification Result:");
        System.out.println("\nMean Max Output:");
        System.out.println("Fuzzy Set: " + result.getFirst() + ", Crisp Value: " + result.getSecond());
        System.out.println("\nWeight Average Mean Output:");
        System.out.println( "Fuzzy Set: " + weighted_output.getFirst() + ", Crisp Value:  " + weighted_output.getSecond());   
        


        IEngine engine2 = new SugenoEngine();
        List<IRule> rules2= new ArrayList<>();
               
        RuleStorage storage02 =new RuleStorage("C:\\Users\\lojay\\Downloads\\Fuzzyyyyyyyyyyyy\\softComputingLibrary\\sugino.json");
        RuleEditor editor02 =new RuleEditor(storage02);
        rules2 = editor02.getAll();

        if (rules2.isEmpty()) {
            System.out.println("Error Reading Json File\n");
            return;
        }

       engine2.fuzzify(input, 
            List.of(new FuzzyVariables.Variable(studyPreparationVar, sp_fs,0, 100 ) , 
                    new FuzzyVariables.Variable(subjectDifficultyVar, sd_fs,1, 10),
                    new FuzzyVariables.Variable(sleepQualityVar, sq_fs,0, 10))
        );

        Map<String, Double> variablesMap = Map.of(
            "Study_Preparation", input.get(0),
            "Subject_Difficulty", input.get(1),
            "Sleep_Quality", input.get(2)
        );

        Map<String, Set<Pair>> res = engine2.inferRules(rules2, variablesMap);
         for (Map.Entry<String, Set<Pair>> entry : res.entrySet()) {
            String key = entry.getKey();
            Set<Pair> valueSet = entry.getValue();
            System.out.print(key + ": ");
            for (Pair pair : valueSet) {
                System.out.print("[" + pair.getFirst() + ", " + pair.getSecond() + "] ");
            }
            System.out.println();
        }

    

        // Lab Example ---------------------------------------------------------------------------------


        List<Double> inputLab = List.of(60.0,25.0);


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
        
        
        IEngine engineLab = new MamdaniEngine() ;
                
        RuleStorage storageLab =new RuleStorage("C:\\Users\\lojay\\Downloads\\Fuzzyyyyyyyyyyyy\\softComputingLibrary\\rules.json");
        RuleEditor editorLab =new RuleEditor(storageLab);
        List<IRule> rulesLab  = editorLab.getAll();

        if (rulesLab.isEmpty()) {
            System.out.println("Error Reading Json File\n");
            return;
        }
        for(IRule r : rulesLab){
            System.out.println("Rule Condition: " + r.getCondition() + " => Consequence: " + r.getConsequence());
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
        
        
        IDefuzzification defuzzLab = new Defuzzification.WeightAverageMean<>();
        //IDefuzzification defuzzLab = new MeanMax();


        Pair resultLab = defuzzLab.defuzzify(outputfs, mpLab);
        System.out.println("Defuzzified Output: Set Name = " + resultLab.getFirst() + ", Value = " + resultLab.getSecond());


    }
}


