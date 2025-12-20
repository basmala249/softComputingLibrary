package NN.ActivationFunction;

import java.util.ArrayList;

public class TanhActivationFunction implements IActivationFunction {
    @Override
    public double getActivation(double input) {
        return Math.tanh(input);
    }

    @Override
    public double getDerivative(double input) {
        double tanh = getActivation(input);
        return 1 - tanh * tanh;
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
