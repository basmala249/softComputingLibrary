package NN.ActivationFunction;

import java.util.ArrayList;

public class LinearActivationFunction implements IActivationFunction {
    @Override
    public double getActivation(double input) {
        return input;
    }

    @Override
    public double getDerivative(double input) {
        return 1;
    }

    @Override
    public ArrayList<Double> getBatchActivation(ArrayList<Double> inputs) {
        
        return inputs;
    }

    @Override
    public ArrayList<Double> getBatchDerivative(ArrayList<Double> inputs) {
        ArrayList<Double> derivatives = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
            derivatives.add(1.0);
        }
        return derivatives;
    }
    
}
