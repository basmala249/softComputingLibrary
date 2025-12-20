package NN.WeightsInitialization;

import java.util.ArrayList;

public class XavierInitializer implements IWeightInitializer {
    private double mn;
    private double mx;


    @Override 
    public ArrayList<ArrayList<Double>> InitializeWeights(int numberOfNeurons, int numberOfInputs) {

        this.mn = -Math.sqrt(6.0 / (numberOfInputs + numberOfNeurons));
        this.mx = Math.sqrt(6.0 / (numberOfInputs + numberOfNeurons));

        ArrayList<ArrayList<Double>> weights = new ArrayList<>();
        for (int i = 0; i < numberOfNeurons; i++) {
            ArrayList<Double> neuronWeights = new ArrayList<>();
            for (int j = 0; j < numberOfInputs; j++) {
                    neuronWeights.add(Math.random() * (mx - mn) + mn); 
            }
            weights.add(neuronWeights);
        }

        return weights;
    }
    
}
