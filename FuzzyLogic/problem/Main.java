package problem;

import java.util.ArrayList;

import java.util.List;

import java.util.Objects;


import FuzzySet.FuzzySet;
import InferenceEngine.IEngine;
import InferenceEngine.MamdaniEngine;
import MemberFunction.IMemberFunction;
import MemberFunction.TrapzoidFunction;
import Rule.IRule;

public class Main {
    public static Integer INF = 1000000;
    public static void main(String[] args) {
        List<Double> input = List.of(60.0,25.0);

        // Variable 1
        String Variable = "dirt" , fuzzySet1 = "small" , fuzzySet2 = "medium" , fuzzySet3 = "large";
        FuzzySet fs = new FuzzySet();
        IMemberFunction mf1 = new TrapzoidFunction(fuzzySet1, List.of(0.0, 0.0, 20.0, 40.0));
        List<Double> yValues = new ArrayList<>();

        yValues = getY(mf1.getPoints());
        mf1.setY(yValues);
        fs.addMemberFunction(mf1);
        IMemberFunction mf2 = new TrapzoidFunction(fuzzySet2, List.of(20.0, 40.0, 60.0, 80.0));
        yValues = getY(mf2.getPoints());
        mf2.setY(yValues);
        fs.addMemberFunction(mf2);

        IMemberFunction mf3 = new TrapzoidFunction(fuzzySet3, List.of(60.0, 80.0, 100.0, 100.0));
        yValues = getY(mf3.getPoints());
        mf3.setY(yValues);
        fs.addMemberFunction(mf3);



        // Variable 2
        String Variable1 = "fabric" , fuzzySet12 = "soft" , fuzzySet23 = "ordinary" , fuzzySet34 = "stiff";
        FuzzySet fs1 = new FuzzySet();
        IMemberFunction mf12 = new TrapzoidFunction(fuzzySet12, List.of(0.0, 0.0, 20.0, 40.0));
        List<Double> yValues1 = new ArrayList<>();

        yValues1 = getY(mf12.getPoints());
        mf12.setY(yValues1);
        fs1.addMemberFunction(mf12);
        IMemberFunction mf23 = new TrapzoidFunction(fuzzySet23, List.of(20.0, 40.0, 60.0, 80.0));
        yValues1 = getY(mf23.getPoints());
        mf23.setY(yValues1);
        fs1.addMemberFunction(mf23);
        IMemberFunction mf34 = new TrapzoidFunction(fuzzySet34, List.of(60.0, 80.0, 100.0, 100.0));
        yValues1 = getY(mf34.getPoints());
        mf34.setY(yValues1);
        fs1.addMemberFunction(mf34);
        
        
        IEngine engine = new MamdaniEngine() ;
        System.out.println("Fuzzification Results:");
        engine.fuzzify(input, List.of(new FuzzyVariables.Variable(Variable, fs) , new FuzzyVariables.Variable(Variable1, fs1)));
        
        IRule rule1 = new Rule.MamdaniRule("(dirt is small & fabric is soft)", "wash time is short");
        IRule rule2 = new Rule.MamdaniRule("(dirt is medium & fabric is ordinary)", "wash time is medium");
        IRule rule5 = new Rule.MamdaniRule("(dirt is large & fabric is not soft)", "wash time is long");
        IRule rule6 = new Rule.MamdaniRule("(dirt is large & fabric is soft)", "wash time is long");
        IRule rule3 = new Rule.MamdaniRule("((dirt is small & fabric is not soft) | (dirt is medium & fabric is soft))", "wash time is short");
        IRule rule4 = new Rule.MamdaniRule("(dirt is medium & fabric is stiff)", "wash time is medium");
        System.out.println("Inference Results:");
        engine.inferRules(List.of(rule1, rule2, rule3, rule4, rule5, rule6));
    }
    public static List<Double> getY(List<Double> inputs) {
        List<Double> yValues = new ArrayList<>();
        for(int i = 0; i < inputs.size(); i++) {
            if(inputs.get(i) == (double) INF) {
                yValues.add(1.0);
            } else{
                if(i == 0 || i == inputs.size() - 1) {
                    yValues.add(0.0);
                } else {
                    yValues.add(1.0);
                }
            }
        }
        return yValues;
    }
    static class pair{
        String first;
        double second;
        pair(String first, double second){
            this.first=first;
            this.second=second;
        }
        String getFirst() {
            return first;
        }
        double getSecond() {
            return second;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            pair pair = (pair) o;
            return Double.compare(pair.second, second) == 0 &&
                first.equals(pair.first);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    } ;
}
