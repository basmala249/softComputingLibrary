package NN.DataHandlying;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tech.tablesaw.api.Table;

public class TrainTestSplit {

    Table table;

    public TrainTestSplit(Table table) {
        this.table = table;
    }

    public List<Table> split(double trainSize) {

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < table.rowCount(); i++) {
            indices.add(i);
        }

        Collections.shuffle(indices);

        int[] indexArray = indices.stream()
                                  .mapToInt(Integer::intValue)
                                  .toArray();

        Table shuffled = table.rows(indexArray);

        int splitIndex = (int) (trainSize * shuffled.rowCount());

        Table train = shuffled.first(splitIndex);
        Table test = shuffled.dropRange(0, splitIndex);

        return List.of(train, test);
    }
}
