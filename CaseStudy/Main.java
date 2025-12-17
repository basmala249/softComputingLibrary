package CaseStudy;


import java.util.List;
import tech.tablesaw.api.Table;
import DataHandlying.OneHotEncoding;
import DataHandlying.TrainTestSplit;

public class Main {
    public static void main(String[] args) throws Exception {
        Table table = Table.read().csv("iris_data.csv");
        OneHotEncoding encoder = new OneHotEncoding(table);
        table = encoder.encode();
        System.out.println(table.first(5));
        TrainTestSplit splitter = new TrainTestSplit(table);
        List<Table> split = splitter.split(0.8);
        Table train = split.get(0);
        Table test = split.get(1); 

    }
}
