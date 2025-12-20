package NN.WeightsInitialization;
import java.util.ArrayList;


public class RandomInitializer implements IWeightInitializer {
    private int mn;
    private int mx;

    public RandomInitializer(int mn, int mx) {
        this.mn = mn;
        this.mx = mx;
    }


    @Override 
    public ArrayList<ArrayList<Double>> InitializeWeights(int numberOfNeurons, int numberOfInputs) {

        ArrayList<ArrayList<Double>> weights = new ArrayList<>();
        for (int i = 0; i < numberOfNeurons; i++) {
            ArrayList<Double> neuronWeights = new ArrayList<>();
            for (int j = 0; j < numberOfInputs; j++) {
                neuronWeights.add(mn + Math.random() * (mx - mn)); 
            }
            weights.add(neuronWeights);
        }

        return weights;
    }
    
}
