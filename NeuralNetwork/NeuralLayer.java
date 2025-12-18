package NeuralNetwork;

import java.util.ArrayList;

import WeightsInitialization.IWeightInitializer;
import ActivationFunction.IActivationFunction;

public class NeuralLayer {
    public int numberOfNeurons;
    public int numberOfInputs;
    private ArrayList<Double> lastInput;
    private ArrayList<Double> outputs;
    private ArrayList<Double> alphas;
    private IActivationFunction activationFunction;
    private ArrayList<ArrayList<Double>> weights = null;
    private IWeightInitializer weightInitializer;



    private ArrayList<Double> zs;
    private ArrayList<Double> biases;



    // Gradient accumulators for batching
    private ArrayList<ArrayList<Double>> gradWeights;
    private ArrayList<Double> gradBiases;



    public NeuralLayer(int numberOfNeurons, int numberOfInputs, IActivationFunction activationFunction, IWeightInitializer weightInitializer) {
        this.numberOfNeurons = numberOfNeurons;
        this.numberOfInputs = numberOfInputs;
        this.activationFunction = activationFunction;
        this.weightInitializer = weightInitializer;
        InitializeLayerWeights();
        clearGrads(); // Initialize gradients
    }



    private void InitializeLayerWeights() {
        weights = weightInitializer.InitializeWeights(numberOfNeurons, numberOfInputs);
        biases = new ArrayList<>();
        for (int i = 0; i < numberOfNeurons; i++) {
            biases.add(1.0); // Or use initializer for biases
        }
    }

    public ArrayList<Double> ApplyForward(ArrayList<Double> inputs) {
        this.lastInput = new ArrayList<>(inputs);
        this.outputs = new ArrayList<>();
        this.zs = new ArrayList<>();

        ArrayList<Double> preActs = new ArrayList<>();
        for (int i = 0; i < numberOfNeurons; i++) {
            double neuronOutput = biases.get(i);
            for (int j = 0; j < numberOfInputs; j++) {
                neuronOutput += weights.get(i).get(j) * inputs.get(j);
            }
            preActs.add(neuronOutput);
            zs.add(neuronOutput);
        }

        // Use batch activation for all (handles scalar or vector)
        outputs = activationFunction.getBatchActivation(preActs);
        return outputs;
    }

    // Set alphas (deltas) externally (from NN backward)
    public void setAlphas(ArrayList<Double> alphas) {
        this.alphas = alphas;
    }

    // Compute errors to pass to previous layer (W^T * alphas)
    public ArrayList<Double> computeBackErrors() {
        ArrayList<Double> errors = new ArrayList<>();
        for (int j = 0; j < numberOfInputs; j++) {
            double error = 0.0;
            for (int i = 0; i < numberOfNeurons; i++) {
                error += alphas.get(i) * weights.get(i).get(j);
            }
            errors.add(error);
        }
        return errors;
    }

    // Accumulate gradients (called after setting alphas)
    public void accumulateGrads() {
        for (int i = 0; i < numberOfNeurons; i++) {
            for (int j = 0; j < numberOfInputs; j++) {
                double gw = gradWeights.get(i).get(j) + alphas.get(i) * lastInput.get(j);
                gradWeights.get(i).set(j, gw);
            }
            double gb = gradBiases.get(i) + alphas.get(i);
            gradBiases.set(i, gb);
        }
    }

    public void updateWeights(double learningRate, int batchSize) {
        double avgFactor = learningRate / batchSize;
        for (int i = 0; i < numberOfNeurons; i++) {
            for (int j = 0; j < numberOfInputs; j++) {
                double newWeight = weights.get(i).get(j) - avgFactor * gradWeights.get(i).get(j);
                weights.get(i).set(j, newWeight);
            }
            double newBias = biases.get(i) - avgFactor * gradBiases.get(i);
            biases.set(i, newBias);
        }
        clearGrads();
    }

    public void clearGrads() {
        gradWeights = new ArrayList<>();
        gradBiases = new ArrayList<>();
        for (int i = 0; i < numberOfNeurons; i++) {
            ArrayList<Double> row = new ArrayList<>();
            for (int j = 0; j < numberOfInputs; j++) {
                row.add(0.0);
            }
            gradWeights.add(row);
            gradBiases.add(0.0);
        }
    }

    // Getters for debugging
    public ArrayList<Double> getOutputs() { return outputs; }
    public ArrayList<ArrayList<Double>> getWeights() { return weights; }
    public ArrayList<Double> getBiases() { return biases; }
    public IActivationFunction getActivationFunction() { return activationFunction; }
}