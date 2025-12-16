package NeuralNetwork;


import java.util.ArrayList;

import WeightsInitialization.IWeightInitializer;
import ActivationFunction.IActivationFunction;

public class NeuralLayer {
    private int numberOfNeurons;
    private int numberOfInputs;
    private ArrayList<Double> lastInput ;
    private ArrayList<Double> outputs;


    private ArrayList<Double> alphas;
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
        this.lastInput = new ArrayList<>(inputs);
        this.outputs = new ArrayList<>();
        for(int i = 0;i < numberOfNeurons;i++) {
            double neuronOutput = 0.0;

            for(int j = 0;j < numberOfInputs;j++) {
                neuronOutput +=  weights.get(i).get(j) * inputs.get(j);
            }
            outputs.add(activationFunction.getActivation(neuronOutput));

        }
        return outputs;
    }
    public ArrayList<Double> BackwardOutlayer (ArrayList<Double> inputs){
        alphas = new ArrayList<>();
        ArrayList<Double> alphaOutPut = new ArrayList<>();

        for(int i = 0 ; i < numberOfInputs;i++){
            alphaOutPut.add(0.0);
        }
        for(int i = 0 ;i < numberOfNeurons ;i++){
            double out = outputs.get(i);
            double target = inputs.get(i);

            double alpha = out * (1 - out) * ( out - target);
            alphas .add(alpha);

            for (int j = 0 ; j < numberOfInputs; j++){
                double error = alphaOutPut.get(j);
                alphaOutPut.set(j,error+alpha * weights.get(i).get(j));
            }
        }
        return alphaOutPut;
    }
    public ArrayList<Double> BackwardHidden(ArrayList<Double> preLyeralpha){
        alphas = new ArrayList<>();
        ArrayList <Double> outputalpha = new ArrayList<>();
        for (int i = 0 ; i < numberOfInputs ;i++){
            outputalpha.add(0.0);
        }

        for(int i = 0 ; i < numberOfNeurons;i++){
            double out = outputs.get(i);
            double alpha =out * (1 - out )* preLyeralpha.get(i) ;
            alphas.add(alpha);
            for(int j = 0; j < numberOfInputs; j++) {
                double error = outputalpha.get(j);
                outputalpha.set(j, error + alpha * weights.get(i).get(j));
            }
        }
        return outputalpha;

    }

    public void UpdateWeights(double learningRate) {
        for(int i = 0; i < numberOfNeurons; i++) {
            for(int j = 0; j < numberOfInputs; j++) {
                double newWeight = weights.get(i).get(j) + learningRate * alphas.get(i) * lastInput.get(j);
                weights.get(i).set(j, newWeight);
            }
        }
    }


    public void InitializeLayerWeights() {
        weights = weightInitializer.InitializeWeights(this.numberOfNeurons, this.numberOfInputs);
    }


}
