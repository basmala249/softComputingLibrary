package CaseStudy;

import java.util.ArrayList;
import java.util.List;

import tech.tablesaw.api.Table;
import tech.tablesaw.api.IntColumn;
import DataHandlying.NormalizeValues;
import DataHandlying.OneHotEncoding;
import DataHandlying.TrainTestSplit;
import NeuralNetwork.NeuralNetwork;
import NeuralNetwork.NeuralLayer;
import ActivationFunction.ReluActivationFunction;
import ActivationFunction.SoftmaxActivationFunction;
import WeightsInitialization.XavierInitializer;
import LossFunctions.CrossEntropy;

public class Main {
    public static void main(String[] args) throws Exception {
        Table table = Table.read().csv("iris_data.csv");
        OneHotEncoding encoder = new OneHotEncoding(table);
        table = encoder.encode();

        TrainTestSplit splitter = new TrainTestSplit(table);
        List<Table> split = splitter.split(0.8);
        Table train = split.get(0);
        Table test = split.get(1);

        // Normalize features only (using train stats for both)
        String[] features = {"sepal-length", "sepal-width", "petal-length", "petal-width"};
        Table trainFeatures = train.select(features);
        NormalizeValues norm = new NormalizeValues(trainFeatures);
        trainFeatures = norm.normalize(); // Normalize train
        // Replace in original train (if needed; here we use separately)

        Table testFeatures = test.select(features);
        testFeatures = norm.apply(testFeatures); // Apply same to test

        ArrayList<ArrayList<Double>> X_train = tableToArrayList(trainFeatures);
        ArrayList<ArrayList<Double>> X_test = tableToArrayList(testFeatures);

        // Prepare Y_train as list of [0,1,0] etc.
        String[] labels = {"Iris-setosa", "Iris-versicolor", "Iris-virginica"};
        ArrayList<ArrayList<Double>> Y_train = new ArrayList<>();
        for (int i = 0; i < train.rowCount(); i++) {
            ArrayList<Double> y = new ArrayList<>();
            for (String label : labels) {
                y.add((double) train.intColumn(label).get(i));
            }
            Y_train.add(y);
        }

        ArrayList<ArrayList<Double>> Y_test = new ArrayList<>();
        for (int i = 0; i < test.rowCount(); i++) {
            ArrayList<Double> y = new ArrayList<>();
            for (String label : labels) {
                y.add((double) test.intColumn(label).get(i));
            }
            Y_test.add(y);
        }

        // Print for verification (optional)
        // for (int i = 0; i < X_train.size(); i++) {
        //     System.out.println(X_train.get(i) + " => " + Y_train.get(i));
        // }

        // Create and train NN
        NeuralNetwork nn = new NeuralNetwork(0.01, 200, 10, new CrossEntropy(), 0.001);
        NeuralLayer hidden = new NeuralLayer(5, 4, new ReluActivationFunction(), new XavierInitializer());
        nn.addLayer(hidden);
        NeuralLayer output = new NeuralLayer(3, 5, new SoftmaxActivationFunction(), new XavierInitializer());
        nn.addLayer(output);

        nn.train(X_train, Y_train);

        // Predict and evaluate
        ArrayList<ArrayList<Double>> preds = nn.predictBatch(X_test);
        int correct = 0;
        for (int i = 0; i < preds.size(); i++) {
            int predClass = argmax(preds.get(i));
            int trueClass = argmax(Y_test.get(i));
            if (predClass == trueClass) correct++;
        }
        double accuracy = (double) correct / X_test.size();
        System.out.println("Test Accuracy: " + accuracy);
    }

    private static int argmax(ArrayList<Double> list) {
        int maxIdx = 0;
        double maxVal = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > maxVal) {
                maxVal = list.get(i);
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public static ArrayList<ArrayList<Double>> tableToArrayList(Table table) {
        ArrayList<ArrayList<Double>> result = new ArrayList<>();
        int rows = table.rowCount();
        int cols = table.columnCount();

        for (int i = 0; i < rows; i++) {
            ArrayList<Double> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(table.doubleColumn(j).get(i)); // Assume double now
            }
            result.add(row);
        }
        return result;
    }
}