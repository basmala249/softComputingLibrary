package LossFunctions;
import java.util.ArrayList;

public class MeanSquaredErrorLoss implements ILossFunction {

    
    public double computeLoss(ArrayList<Double> predicted, ArrayList<Double> actual) {
        double sumSquaredError = 0.0;
        int n = predicted.size();

        for (int i = 0; i < n; i++) {
            double error = actual.get(i) - predicted.get(i);
            sumSquaredError += Math.pow(error, 2);
        }

        return sumSquaredError / n;
    }
    
}
