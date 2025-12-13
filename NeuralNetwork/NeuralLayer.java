package NeuralNetwork;


import java.util.ArrayList;

import WeightsInitialization.IWeightInitializer;
import ActivationFunction.IActivationFunction;

public class NeuralLayer {
    private int numberOfNeurons;
    private int numberOfInputs;

    private IActivationFunction activationFunction;


    private ArrayList<ArrayList<Double>> weights = null;
    private IWeightInitializer weightInitializer;

    public NeuralLayer(int numberOfNeurons, int numberOfInputs, IActivationFunction activationFunction, IWeightInitializer weightInitializer) {
        this.numberOfNeurons = numberOfNeurons;
        this.numberOfInputs = numberOfInputs;
        this.weightInitializer = weightInitializer;
        this.activationFunction = activationFunction;
        
        InitializeLayerWeights();
    }

    public ArrayList<Double> ApplyForward(ArrayList<Double> inputs) {
        
        ArrayList<Double> outputs = new ArrayList<>();

        for(int i = 0;i < numberOfNeurons;i++) {
            double neuronOutput = 0.0;

            for(int j = 0;j < numberOfInputs;j++) {
                
                neuronOutput +=  weights.get(i).get(j) * inputs.get(j);
            }
            outputs.add(activationFunction.getActivation(neuronOutput));

        }
        return outputs;
    
    
    }

    public void InitializeLayerWeights() {
        weights = weightInitializer.InitializeWeights(this.numberOfNeurons, this.numberOfInputs);
    }

    
}
