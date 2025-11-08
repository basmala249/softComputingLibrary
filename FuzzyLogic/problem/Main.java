package problem;

import java.util.ArrayList;
import java.util.List;

import FuzzySet.FuzzySet;
import MemberFunction.IMemberFunction;
import MemberFunction.TrapzoidFunction;

public class Main {
    public static Integer INF = 1000000;
    public static void main(String[] args) {
        FuzzySet<Double> fs = new FuzzySet<>();
        List<Double> inputs = List.of(0.0, 0.0, 20.0, 40.0);
        IMemberFunction<Double> mf1 = new TrapzoidFunction<Double>(inputs);
        List<Double> yValues = new ArrayList<>();

        yValues = getY(mf1.getPoints());
        mf1.setY(yValues);
        fs.addMemberFunction(mf1);

        IMemberFunction<Double> mf2 = new TrapzoidFunction<Double>(List.of(20.0, 40.0, 60.0, 80.0));
        yValues = getY(mf2.getPoints());
        mf2.setY(yValues);
        fs.addMemberFunction(mf2);

        IMemberFunction<Double> mf3 = new TrapzoidFunction<Double>(List.of(60.0, 80.0, 100.0, 100.0));
        yValues = getY(mf3.getPoints());
        mf3.setY(yValues);
        fs.addMemberFunction(mf3);
        for(IMemberFunction<Double> mf : fs.getMemberFunctions()) {
            System.out.println("Membership value at 60: " + mf.getMembershipValue(25.0));
        }
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

}
