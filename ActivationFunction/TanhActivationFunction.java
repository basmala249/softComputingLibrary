package ActivationFunction;


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
    
}
