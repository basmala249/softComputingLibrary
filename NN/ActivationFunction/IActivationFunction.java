package NN.ActivationFunction;

import java.util.ArrayList;

public interface IActivationFunction {
    double getActivation(double input);
    double getDerivative(double input);

    ArrayList<Double> getBatchActivation(ArrayList<Double> inputs);
    ArrayList<Double> getBatchDerivative(ArrayList<Double> inputs);
}
