package ActivationFunction;

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
    
}
