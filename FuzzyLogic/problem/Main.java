package problem;

import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.HashSet;
import java.util.List;
// import java.util.Map;
import java.util.Objects;
// import java.util.Set;

// import FuzzySet.FuzzySet;
// import MemberFunction.IMemberFunction;
// import MemberFunction.TrapzoidFunction;

public class Main {
    public static Integer INF = 1000000;
    public static void main(String[] args) {
        // String Variable = "Temperature" , fuzzySet1 = "Cold" , fuzzySet2 = "Warm" , fuzzySet3 = "Hot";
        // List<String> FuzzysetNames = List.of(fuzzySet1, fuzzySet2, fuzzySet3);
        // FuzzySet<Double> fs = new FuzzySet<>();
        // List<Double> inputs = List.of(0.0, 0.0, 20.0, 40.0);
        // IMemberFunction<Double> mf1 = new TrapzoidFunction<Double>(inputs);
        // List<Double> yValues = new ArrayList<>();

        // yValues = getY(mf1.getPoints());
        // mf1.setY(yValues);
        // fs.addMemberFunction(mf1);

        // IMemberFunction<Double> mf2 = new TrapzoidFunction<Double>(List.of(20.0, 40.0, 60.0, 80.0));
        // yValues = getY(mf2.getPoints());
        // mf2.setY(yValues);
        // fs.addMemberFunction(mf2);

        // IMemberFunction<Double> mf3 = new TrapzoidFunction<Double>(List.of(60.0, 80.0, 100.0, 100.0));
        // yValues = getY(mf3.getPoints());
        // mf3.setY(yValues);
        // fs.addMemberFunction(mf3);
        // Map<String , Set<pair>> rules = new HashMap<>();
        // Set<pair> tempSet = new HashSet<>();
        // for(int i = 0; i < fs.getMemberFunctions().size(); i++) {
        //     IMemberFunction<Double> mf = fs.getMemberFunctions().get(i);
        //     tempSet.add(new pair(FuzzysetNames.get(i), mf.getMembershipValue(25.0)));
        // }
        // rules.put(Variable, tempSet);
        // for(String key : rules.keySet()) {
        //     System.out.println("Variable: " + key);
        //     for(pair p : rules.get(key)) {
        //         System.out.println("Fuzzy Set: " + p.getFirst() + " Membership Value: " + p.getSecond());
        //     }
        // }
        
    }
    public <T extends Number> double AND(T A, T B) {
           return (A.doubleValue() < B.doubleValue()) ? A.doubleValue() : B.doubleValue();
}
    public <T extends Number> double OR(T A, T B) {
      return (A.doubleValue() > B.doubleValue()) ? A.doubleValue() : B.doubleValue();
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
