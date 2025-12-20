package NN.ActivationFunction;

import java.util.ArrayList;

public class SoftmaxActivationFunction implements IActivationFunction {

    @Override
    public double getActivation(double input) {
        // Softmax is not well-defined for a single scalar.
        // Return exp(x) as a placeholder, but real use is via batch.
        return Math.exp(input);
    }

    @Override
    public double getDerivative(double input) {
        // Derivative for a single scalar is not meaningful.
        // Softmax derivative is a Jacobian matrix.
        // Return input*(1-input) as a placeholder (like sigmoid).
        return input * (1 - input);
    }

    @Override
    public ArrayList<Double> getBatchActivation(ArrayList<Double> inputs) {
        ArrayList<Double> outputs = new ArrayList<>();
        // Numerical stability: subtract max
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
        // Softmax derivative is a full Jacobian, not a simple element-wise derivative.
        // For simplicity, return element-wise p*(1-p).
        ArrayList<Double> activations = getBatchActivation(inputs);
        ArrayList<Double> derivatives = new ArrayList<>();
        for (double p : activations) {
            derivatives.add(p * (1 - p));
        }
        return derivatives;
    }
    
    
}
