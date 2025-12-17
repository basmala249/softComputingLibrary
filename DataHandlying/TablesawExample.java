package DataHandlying;
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;




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
        
        TrainTestSplit splitter = new TrainTestSplit(table);
        List<Table> split = splitter.split(0.8);
        Table train = split.get(0);
        Table test = split.get(1);
        System.out.println("Train rows: " + train.rowCount());
        System.out.println("Test rows: " + test.rowCount());
    //     // Step 3: Encode String Columns safely
    //     List<IntColumn> newColumns = new ArrayList<>();
    //     Map<String, Map<String, Integer>> encoders = new HashMap<>();
    //     Map<String, Map<Integer, String>> decoders = new HashMap<>();

    //     for (Column<?> col : table.columns()) {
    //         if (col instanceof StringColumn sc) {
    //             IntColumn encoded = IntColumn.create(sc.name() + "_enc");
    //             Map<String, Integer> encoder = new HashMap<>();
    //             Map<Integer, String> decoder = new HashMap<>();
    //             int id = 0;

    //             for (String v : sc) {
    //                 if (!encoder.containsKey(v)) {
    //                     encoder.put(v, id);
    //                     decoder.put(id, v);
    //                     id++;
    //                 }
    //                 encoded.append(encoder.get(v));
    //             }

    //             newColumns.add(encoded); // add to temporary list
    //             encoders.put(sc.name(), encoder);
    //             decoders.put(sc.name(), decoder);
    //         }
    //     }
    //     table.addColumns(newColumns.toArray(new IntColumn[0]));

    //    

    
    }
}

