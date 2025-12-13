package ActivationFunction;

public class ReluActivationFunction  implements IActivationFunction {
    @Override
    public double getActivation(double input) {
        return Math.max(0, input);
    }

    @Override
    public double getDerivative(double input) {
        return input > 0 ? 1 : 0;
    }
    
}
