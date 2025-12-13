package ActivationFunction;

import java.util.ArrayList;

public class ReluActivationFunction  implements IActivationFunction {
    @Override
    public double getActivation(double input) {
        return Math.max(0, input);
    }

    @Override
    public double getDerivative(double input) {
        return input > 0 ? 1 : 0;
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
