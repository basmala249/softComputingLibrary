package NeuralNetwork;

import java.util.ArrayList;

import ActivationFunction.SoftmaxActivationFunction;
import LossFunctions.CrossEntropy;
import LossFunctions.ILossFunction;
import LossFunctions.MeanSquaredErrorLoss;
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
    private ArrayList<ArrayList<Double>> gradWeights;
    private ArrayList<Double> gradBiases;

    public ArrayList<Double> getOutputs() { return outputs; }
    public ArrayList<ArrayList<Double>> getWeights() { return weights; }
    public ArrayList<Double> getBiases() { return biases; }
    public IActivationFunction getActivationFunction() { return activationFunction; }



    public NeuralLayer(int numberOfNeurons, int numberOfInputs, IActivationFunction activationFunction, IWeightInitializer weightInitializer) {
        this.numberOfNeurons = numberOfNeurons;
        this.numberOfInputs = numberOfInputs;
        this.activationFunction = activationFunction;
        this.weightInitializer = weightInitializer;
        InitializeLayerWeights();
        clearGrads();
    }



    private void InitializeLayerWeights() {
        weights = weightInitializer.InitializeWeights(numberOfNeurons, numberOfInputs);
        biases = new ArrayList<>();
        for (int i = 0; i < numberOfNeurons; i++) {
            biases.add(1.0);
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

        outputs = activationFunction.getBatchActivation(preActs);
        return outputs;
    }


    public ArrayList<Double> BackwardOutlayer(ArrayList<Double> expout, ILossFunction lossFunction) {
        alphas = new ArrayList<>();
        ArrayList<Double> propagatedErrors = new ArrayList<>();
        for (int j = 0; j < numberOfInputs; j++) {
            propagatedErrors.add(0.0);
        }

        boolean isSoftmaxCE = (activationFunction instanceof SoftmaxActivationFunction) && (lossFunction instanceof CrossEntropy);
        ArrayList<Double> ders = activationFunction.getBatchDerivative(outputs);

        for (int i = 0; i < numberOfNeurons; i++) {
            double out = outputs.get(i);
            double target = expout.get(i);
            double error = out - target;
            double alpha;
            if (lossFunction instanceof MeanSquaredErrorLoss) {
                alpha = error * ders.get(i);
            } else if (lossFunction instanceof CrossEntropy) {
                if (isSoftmaxCE) {
                    alpha = error;
                } else {
                    alpha = error * ders.get(i);
                }
            } else {
                throw new UnsupportedOperationException("Unsupported loss");
            }
            alphas.add(alpha);

            for (int j = 0; j < numberOfInputs; j++) {
                double err = propagatedErrors.get(j);
                propagatedErrors.set(j, err + alpha * weights.get(i).get(j));
            }
        }
        return propagatedErrors;
    }

    public ArrayList<Double> BackwardHidden(ArrayList<Double> preLayerAlpha) {
        alphas = new ArrayList<>();
        ArrayList<Double> propagatedErrors = new ArrayList<>();
        for (int j = 0; j < numberOfInputs; j++) {
            propagatedErrors.add(0.0);
        }

        ArrayList<Double> ders = activationFunction.getBatchDerivative(outputs);
        for (int i = 0; i < numberOfNeurons; i++) {
            double alpha = preLayerAlpha.get(i) * ders.get(i);
            alphas.add(alpha);

            for (int j = 0; j < numberOfInputs; j++) {
                double err = propagatedErrors.get(j);
                propagatedErrors.set(j, err + alpha * weights.get(i).get(j));
            }
        }
        return propagatedErrors;
    }

    public void updateWeightsImmediately(double learningRate) {
        for (int i = 0; i < numberOfNeurons; i++) {
            for (int j = 0; j < numberOfInputs; j++) {
                double deltaW = learningRate * alphas.get(i) * lastInput.get(j);
                weights.get(i).set(j, weights.get(i).get(j) - deltaW);
            }
            double deltaB = learningRate * alphas.get(i);
            biases.set(i, biases.get(i) - deltaB);
        }
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


}
