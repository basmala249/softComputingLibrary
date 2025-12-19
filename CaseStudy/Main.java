package CaseStudy;

import java.util.ArrayList;
import java.util.List;

import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.Table;
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

        Table stbnTable = train.select("sepal-length", "sepal-width", "petal-length", "petal-width");
        NormalizeValues norm = new NormalizeValues(stbnTable);
        stbnTable = norm.normalize();
        ArrayList<ArrayList<Double>> X_train = tableToArrayList(stbnTable);
        stbnTable = test.select("sepal-length", "sepal-width", "petal-length", "petal-width");
        stbnTable = norm.apply(stbnTable);
        ArrayList<ArrayList<Double>> X_test = tableToArrayList(stbnTable);

        IntColumn Y1 = train.intColumn("Iris-setosa");
        IntColumn Y2 = train.intColumn("Iris-versicolor");
        IntColumn Y3 = train.intColumn("Iris-virginica");
        ArrayList<ArrayList<Double>> Y_train = new ArrayList<>();
        for (int i = 0; i < Y1.size(); i++) {
            ArrayList<Double> y = new ArrayList<>();
            y.add((double) Y1.get(i));
            y.add((double) Y2.get(i));
            y.add((double) Y3.get(i));
            Y_train.add(y);
        }
        Y1 = test.intColumn("Iris-setosa");
        Y2 = test.intColumn("Iris-versicolor");
        Y3 = test.intColumn("Iris-virginica");
        ArrayList<ArrayList<Double>> Y_test = new ArrayList<>();
        for (int i = 0; i < Y1.size(); i++) {
            ArrayList<Double> y = new ArrayList<>();
            y.add((double) Y1.get(i));
            y.add((double) Y2.get(i));
            y.add((double) Y3.get(i));
            Y_test.add(y);
        }


        NeuralNetwork nn = new NeuralNetwork(0.01, 200, 10, new CrossEntropy(), 0.001);
        NeuralLayer hidden = new NeuralLayer(5, 4, new ReluActivationFunction(), new XavierInitializer());
        nn.addLayer(hidden);
        NeuralLayer output = new NeuralLayer(3, 5, new SoftmaxActivationFunction(), new XavierInitializer());
        nn.addLayer(output);

        nn.train(X_train, Y_train);

        ArrayList<ArrayList<Double>> preds = nn.predictBatch(X_test);
        int correct = 0;
        String[] labels = {"Iris-setosa", "Iris-versicolor", "Iris-virginica"};
        for (int i = 0; i < preds.size(); i++) {
            System.out.println("Test " + i + ": Input = " + X_test.get(i));
            System.out.println("  Actual: " + Y_test.get(i) + " (class " + labels[argmax(Y_test.get(i))]);
            System.out.println("  Predicted: " + preds.get(i) + " (class " + labels[argmax(preds.get(i))]);

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
                row.add((Double) table.column(j).get(i));
            }
            result.add(row);
        }
        return result;
    }
}
