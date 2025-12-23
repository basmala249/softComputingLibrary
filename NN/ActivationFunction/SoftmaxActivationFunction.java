package NN.ActivationFunction;

import java.util.ArrayList;

public class SoftmaxActivationFunction implements IActivationFunction {

    @Override
    public double getActivation(double input) {
        return Math.exp(input);
    }

    @Override
    public double getDerivative(double input) {
        return input * (1 - input);
    }

    @Override
    public ArrayList<Double> getBatchActivation(ArrayList<Double> inputs) {
        ArrayList<Double> outputs = new ArrayList<>();
        double max = inputs.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);

        double sumExp = 0.0;
        for (double x : inputs) {
            sumExp += Math.exp(x - max);
        }

        for (double x : inputs) {
            outputs.add(Math.exp(x - max) / sumExp);
        }
        return outputs;
    }

    @Override
    public ArrayList<Double> getBatchDerivative(ArrayList<Double> inputs) {
        ArrayList<Double> activations = getBatchActivation(inputs);
        ArrayList<Double> derivatives = new ArrayList<>();
        for (double p : activations) {
            derivatives.add(p * (1 - p));
        }
        return derivatives;
    }
    
    
}
