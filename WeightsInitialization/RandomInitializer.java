package WeightsInitialization;
import java.util.ArrayList;


public class RandomInitializer implements IWeightInitializer {

    @Override 
    public ArrayList<ArrayList<Double>> InitializeWeights(int numberOfNeurons, int numberOfInputs) {

        ArrayList<ArrayList<Double>> weights = new ArrayList<>();
        for (int i = 0; i < numberOfNeurons; i++) {
            ArrayList<Double> neuronWeights = new ArrayList<>();
            for (int j = 0; j < numberOfInputs; j++) {
                neuronWeights.add(Math.random()); // momken ne7tag n8ayar da
                // msh 3arfa hya de ely hya 3ayzah walah la2 
            }
            weights.add(neuronWeights);
        }

        return weights;
    }
    
}
