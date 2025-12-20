package NN.ActivationFunction;

import java.util.ArrayList;

public class SigmoidActivationFunction  implements IActivationFunction {
    
    @Override 
    public double getActivation(double input) {
        return 1 / (1 + Math.exp(input));
    }


    @Override 
    public double getDerivative(double input) {
        double sigmoid = getActivation(input);
        return sigmoid * (1 - sigmoid);
    }


    @Override
    public ArrayList<Double> getBatchActivation(ArrayList<Double> inputs) {
        ArrayList<Double> activations = new ArrayList<>();

        for (double input : inputs) {
            activations.add(getActivation(input));
        }
        return activations;
    }

    @Override
    public ArrayList<Double> getBatchDerivative(ArrayList<Double> inputs) {
         ArrayList<Double> activations = new ArrayList<>();

        for (double input : inputs) {
            activations.add(getDerivative(input));
        }
        return activations;
    }
    
}
