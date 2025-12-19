package NeuralNetwork;

import LossFunctions.ILossFunction;
import LossFunctions.MeanSquaredErrorLoss;
import LossFunctions.CrossEntropy;
import ActivationFunction.SoftmaxActivationFunction;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {

    private double learningRate = 0.01;
    private int epochs = 100;
    private int batchSize = 1;
    private ILossFunction lossFunction;
    private double accLoss = 0.01;
    private ArrayList<Double> trainingLossHistory = new ArrayList<>();

    private List<NeuralLayer> layers = new ArrayList<>();

    public void setLearningRate(double lr) { this.learningRate = lr; }

    public void setEpochs(int epochs) {this.epochs = epochs;}
    public void setBatchSize(int batchSize) {this.batchSize = batchSize;}
    public void setAccLoss(double accLoss) {this.accLoss = accLoss;}
    public ArrayList<Double> getTrainingLossHistory() { return trainingLossHistory; }
    public NeuralLayer getLayer(int index) { return layers.get(index); }
    public NeuralNetwork() {}

    public NeuralNetwork(double learningRate, int epochs, int batchSize, ILossFunction lossFunction, double acceptableLoss) {
        this.learningRate = learningRate;
        this.epochs = epochs;
        this.batchSize = batchSize;
        this.lossFunction = lossFunction;
        this.accLoss = acceptableLoss;
    }

    public void addLayer(NeuralLayer layer) {
        if (!layers.isEmpty()) {
            NeuralLayer prev = layers.get(layers.size() - 1);
            if (prev.numberOfNeurons != layer.numberOfInputs) {
                throw new IllegalArgumentException("Input size mismatch: " + layer.numberOfInputs + " != " + prev.numberOfNeurons);
            }
        }
        layers.add(layer);
    }

    public void train(ArrayList<ArrayList<Double>> inputs, ArrayList<ArrayList<Double>> expectedOutputs) {
        if (inputs.size() != expectedOutputs.size()) {
            throw new IllegalArgumentException("Inputs and outputs size mismatch");
        }

        for (int epoch = 0; epoch < epochs; epoch++) {
            double epochLoss = 0.0;
            for (int b = 0; b < inputs.size(); b += batchSize) {
                int end = Math.min(b + batchSize, inputs.size());
                double batchLoss = 0.0;

                for (NeuralLayer layer : layers) {
                    layer.clearGrads();
                }

                for (int i = b; i < end; i++) {
                    ArrayList<Double> output = forwardPass(inputs.get(i));
                    double sampleLoss = lossFunction.computeLoss(output, expectedOutputs.get(i));
                    batchLoss += sampleLoss;
                    backwardPass(expectedOutputs.get(i));

                    for (NeuralLayer layer : layers) {
                        layer.updateWeightsImmediately(learningRate);
                    }
                }
                epochLoss += batchLoss;


            }

            double avgLoss = epochLoss / inputs.size();
            trainingLossHistory.add(avgLoss);
            System.out.println("Epoch " + (epoch + 1) + "/" + epochs + " - Loss: " + avgLoss);

            if (avgLoss < accLoss) {
                System.out.println("Target loss reached! Stopping early.");
                break;
            }
        }
    }

    private ArrayList<Double> forwardPass(ArrayList<Double> input) {
        for (NeuralLayer layer : layers) {
            input = layer.ApplyForward(input);
        }
        return input;
    }

    private void backwardPass(ArrayList<Double> expout) {
        ArrayList<Double> errors = null;
        for (int i = layers.size() - 1; i >= 0; i--) {
            NeuralLayer layer = layers.get(i);
            if (i == layers.size() - 1) {
                errors = layer.BackwardOutlayer(expout, lossFunction);
            } else {
                errors = layer.BackwardHidden(errors);
            }
        }
    }

    public ArrayList<Double> predict(ArrayList<Double> input) {
        return forwardPass(input);
    }

    public ArrayList<ArrayList<Double>> predictBatch(ArrayList<ArrayList<Double>> inputs) {
        ArrayList<ArrayList<Double>> predictions = new ArrayList<>();
        for (ArrayList<Double> input : inputs) {
            predictions.add(forwardPass(input));
        }
        return predictions;
    }


}
