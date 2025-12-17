package CaseStudy;

import java.util.ArrayList;
import java.util.List;

import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.NumberColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import DataHandlying.OneHotEncoding;
import DataHandlying.TrainTestSplit;
import java.lang.reflect.Array;


public class Main {
    public static void main(String[] args) throws Exception {
        Table table = Table.read().csv("iris_data.csv");
        OneHotEncoding encoder = new OneHotEncoding(table);
        table = encoder.encode();

        //System.out.println(table.first(5)); 
        TrainTestSplit splitter = new TrainTestSplit(table);
        List<Table> split = splitter.split(0.8);
        Table train = split.get(0);
        Table test = split.get(1);
        
        Table stbnTable = train.select("sepal-length", "sepal-width", "petal-length", "petal-width");
        ArrayList<ArrayList<Double>> X_train = tableToArrayList(stbnTable);
        stbnTable = test.select("sepal-length", "sepal-width", "petal-length", "petal-width");
        ArrayList<ArrayList<Double>> X_test = tableToArrayList(stbnTable);

        IntColumn Y1 = train.intColumn("Iris-setosa"); 
        IntColumn Y2 = train.intColumn("Iris-versicolor");
        IntColumn Y3 = train.intColumn("Iris-virginica");
        ArrayList<Double> Y1_Train = new ArrayList<>();
        ArrayList<Double> Y2_Train = new ArrayList<>();
        ArrayList<Double> Y3_Train = new ArrayList<>();
        for (int i = 0; i < Y1.size(); i++) {
            Y1_Train.add((double) Y1.get(i));
            Y2_Train.add((double) Y2.get(i));
            Y3_Train.add((double) Y3.get(i));
        }
        Y1 = test.intColumn("Iris-setosa");
        Y2 = test.intColumn("Iris-versicolor");
        Y3 = test.intColumn("Iris-virginica");
        ArrayList<Double> Y1_Test = new ArrayList<>();
        ArrayList<Double> Y2_Test = new ArrayList<>();
        ArrayList<Double> Y3_Test = new ArrayList<>();
        for (int i = 0; i < Y1.size(); i++) {
            Y1_Test.add((double) Y1.get(i));
            Y2_Test.add((double) Y2.get(i));
            Y3_Test.add((double) Y3.get(i));
        }
        for(int i = 0 ; i < X_train.size(); i++) {
            System.out.println(X_train.get(i) + " => " + Y1_Train.get(i) + ", " + Y2_Train.get(i) + ", " + Y3_Train.get(i));
        }
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
