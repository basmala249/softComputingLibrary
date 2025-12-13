package NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {

    private double learningRate;
    private int epochs;
    private int batchSize;



    private List<NeuralLayer> layers = new ArrayList<>();   

    public NeuralNetwork(double learningRate, int epochs, int batchSize) {
        this.learningRate = learningRate;
        this.epochs = epochs;
        this.batchSize = batchSize;
    }


    public void addLayer(NeuralLayer layer) {

        if(!layers.isEmpty()) {
           // must validate here the number of input 
        }
        layers.add(layer);
    }

    public void train(ArrayList<ArrayList<Double>> inputs, ArrayList<ArrayList<Double>> expectedOutputs) {
        
        // For each epoch (repeat full dataset N times):
        
        // Shuffle your data
        
        // Split data into batches:
        
        
        // Take batch_size examples at a time from inputs/expectedOutputs
        
        // For each batch:
        
        // Forward pass: Run all inputs through the network (layer by layer)
        
        // Calculate batch loss: Compare network outputs vs expected outputs
        
        // Backward pass (in reverse order):
        
        // Compute error at output layer
        
        
        // Propagate error backwards through each layer
        
        
        // Calculate weight gradients for each layer
        
        // Update weights: Adjust weights using gradients × learning rate
        
        // After each epoch:
        
        // Calculate average loss across all batches
        
        // Store/print this loss for tracking
         
        // Check if loss is low enough (optional early stopping)


    } 
    
    

    private ArrayList<Double> forwardPass(ArrayList<Double> input) {
        for(NeuralLayer layer : layers) {
            input = layer.ApplyForward(input);
        }
        return input;
    }
}
