package NeuralNetwork;

import LossFunctions.ILossFunction;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {

    private double learningRate;
    private double accLoss;
    private int epochs;
    private int batchSize;

    private ILossFunction lossFunction;
    private ArrayList<Double> trainingLossHistory = new ArrayList<>();


    private List<NeuralLayer> layers = new ArrayList<>();   

    public NeuralNetwork(double learningRate, int epochs, int batchSize,ILossFunction lossFunction,double acceptableLoss) {
        this.learningRate = learningRate;
        this.epochs = epochs;
        this.batchSize = batchSize;
        this.lossFunction = lossFunction;
        this.accLoss = acceptableLoss;
    }

    public void addLayer(NeuralLayer layer) {

        if(!layers.isEmpty()) {
           // must validate here the number of input 
        }
        layers.add(layer);
    }

    public void train(ArrayList<ArrayList<Double>> inputs, ArrayList<ArrayList<Double>> expectedOutputs) {
        
        // For each epoch (repeat full dataset N times):
        for (int epoch = 0 ; epoch < epochs;epoch++){
            // TODO Shuffle your data

            // TODO Split data into batches:


            //TODO Take batch_size examples at a time from inputs/expectedOutputs

            // For each batch:
            double epochloss = 0.0;
            for(int i = 0; i < inputs.size();i++){
                // Forward pass: Run all inputs through the network (layer by layer)
                ArrayList<Double> output = forwardPass(inputs.get(i));
                // Calculate batch loss: Compare network outputs vs expected outputs
                double loss = lossFunction.computeLoss(output,expectedOutputs.get(i));
                epochloss += loss;
                // Backward pass (in reverse order):
                backwardPass(expectedOutputs.get(i));

                // Update weights: Adjust weights using gradients × learning rate
                updateAllWeights();
            }
            // After each epoch:

            // Calculate average loss across all batches
            double avgLoss = epochloss / inputs.size();
            trainingLossHistory.add(avgLoss);
            // Store/print this loss for tracking
            System.out.println("Epoch " + (epoch + 1) + "/" + epochs + " - Loss: " + avgLoss);

            // Check if loss is low enough (optional early stopping)
            if(avgLoss < accLoss){
                System.out.println("Target loss reached! Stopping early.");
                break;
            }
        }
    } 
    

    // public ArrayList<ArrayList<Double>> predict(ArrayList<ArrayList<Double>> inputs) {
    //     ArrayList<ArrayList<Double>> outputs = new ArrayList<>();
    //     for(int i = 0; i < inputs.size();i++){
    //         ArrayList<Double> output = forwardPass(inputs.get(i));
    //         outputs.add(output);
    //     }
    //     return outputs;
    // }



    private ArrayList<Double> forwardPass(ArrayList<Double> input) {
        for(NeuralLayer layer : layers) {
            input = layer.ApplyForward(input);
        }
        return input;
    }

    private void  backwardPass(ArrayList<Double> expout) {
        ArrayList<Double> errors = null;

        for(int i = layers.size() -1 ; i >= 0; i++){
            NeuralLayer layer = layers.get(i);
            if(i == layers.size()-1){
                errors = layer.BackwardOutlayer(expout);
            }else {
                errors = layer.BackwardHidden(errors);
            }
        }
    }

    private void updateAllWeights() {
        for(NeuralLayer layer : layers) {
            layer.UpdateWeights(learningRate);
        }
    }
}
