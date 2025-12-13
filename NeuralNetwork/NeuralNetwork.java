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

    } 
    
    
}
