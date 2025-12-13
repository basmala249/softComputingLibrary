package ActivationFunction;


public class LinearActivationFunction implements IActivationFunction {
    @Override
    public double getActivation(double input) {
        return input;
    }

    @Override
    public double getDerivative(double input) {
        return 1;
    }
    
}
