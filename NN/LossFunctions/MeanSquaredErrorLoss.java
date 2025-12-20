package NN.LossFunctions;
import java.util.ArrayList;

public class MeanSquaredErrorLoss implements ILossFunction {

    @Override
    public double computeLoss(ArrayList<Double> predicted, ArrayList<Double> actual) {
        double sumSquaredError = 0.0;
        int n = predicted.size();

        for (int i = 0; i < n; i++) {
            double error = actual.get(i) - predicted.get(i);
            sumSquaredError += Math.pow(error, 2);
        }

        return sumSquaredError / n;
    }
    
    @Override
    public ArrayList<Double> computeGradient(ArrayList<Double> predicted, ArrayList<Double> actual) {
        ArrayList<Double> grad = new ArrayList<Double>(predicted.size());
        double scale = 2.0 / actual.size();
        for (int i = 0; i < predicted.size(); i++) {
            grad.add(i, scale * (predicted.get(i) - actual.get(i))) ;
        }
        return grad;
    }
}
