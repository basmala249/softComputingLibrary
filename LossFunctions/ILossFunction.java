package LossFunctions;

import java.util.ArrayList;

public interface ILossFunction {
    
    double computeLoss(ArrayList<Double> predicted, ArrayList<Double> actual);
    ArrayList<Double> computeGradient(ArrayList<Double> predicted, ArrayList<Double> actual);

   
}
