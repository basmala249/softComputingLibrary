package NN.LossFunctions;
import java.util.ArrayList;

public class CrossEntropy implements ILossFunction {

    private static final double EPS = 1e-12; // avoid log(0)


    // L(y, y^)= −∑ yi*log(yi^)
    @Override
    public double computeLoss(ArrayList<Double> predicted, ArrayList<Double> actual) {
       double sum = 0.0;
        for (int i = 0; i < actual.size(); i++) {
            sum += actual.get(i) * Math.log(predicted.get(i) + EPS);
        }
        return -sum;
    }

    @Override
    public ArrayList<Double> computeGradient(ArrayList<Double> predicted, ArrayList<Double> actual) {
        ArrayList<Double> grad = new ArrayList<Double>(predicted.size());
        for (int i = 0; i < predicted.size(); i++) {
            grad.add(i, -(actual.get(i)/ (predicted.get(i) + EPS)));
        }
        return grad;
    }
    
}
