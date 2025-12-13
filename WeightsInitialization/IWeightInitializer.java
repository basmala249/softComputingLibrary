package WeightsInitialization;

import java.util.ArrayList;

public interface IWeightInitializer {

    public ArrayList<ArrayList<Double>> InitializeWeights(int numberOfNeurons, int numberOfInputs);
    
}
