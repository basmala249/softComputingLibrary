package FuzzyLogic.Utils;

import java.util.ArrayList;
import java.util.List;

public class GetY {
    private static final double INF = 1000000;
    public List<Double> getY(List<Double> inputs) {
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
