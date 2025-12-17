package DataHandlying;




import java.util.List;
import tech.tablesaw.api.Table;

public class TablesawExample {
    public static void main(String[] args) throws Exception {

        Table table = Table.read().csv("iris_data.csv");
        System.out.println(table.structure());
        NullValuesHandle nullHandler = new NullValuesHandle(table);
        table = nullHandler.handleNullValues();
        //System.out.println(table);
        NormalizeValues normalizer = new NormalizeValues(table);
        table = normalizer.normalize();
        System.out.println(table);
        OneHotEncoding encoder = new OneHotEncoding(table);
        table = encoder.encode();
        System.out.println(table.last(5));
        TrainTestSplit splitter = new TrainTestSplit(table);
        List<Table> split = splitter.split(0.8);
        Table train = split.get(0);
        Table test = split.get(1);
        System.out.println("Train rows: " + train.rowCount());
        System.out.println("Test rows: " + test.rowCount());

    }
}
