package NN.DataHandlying;

import java.util.*;

import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;

public class OneHotEncoding {

    Table table;

    public OneHotEncoding(Table table) {
        this.table = table;
    }

    public Table encode() {
        List<Column<?>> columns = new ArrayList<>(table.columns());
        List<String> columnsToRemove = new ArrayList<>(); 
        List<Column<?>> columnsToAdd = new ArrayList<>(); 
        for (Column<?> col : columns) {

            if (col instanceof StringColumn) {

                StringColumn labelCol = (StringColumn) col;

                Set<String> uniqueClasses = new LinkedHashSet<>(labelCol.asList());

                Map<String, IntColumn> oneHotColumns = new LinkedHashMap<>();

                // Create columns
                for (String cls : uniqueClasses) {
                    IntColumn newCol = IntColumn.create(cls);
                    for (int i = 0; i < table.rowCount(); i++) {
                        newCol.append(0);
                    }
                    oneHotColumns.put(cls, newCol);
                }
                
                for (int i = 0; i < labelCol.size(); i++) {
                    String value = labelCol.get(i);
                    oneHotColumns.get(value).set(i, 1);
                }

                columnsToAdd.addAll(oneHotColumns.values());
                columnsToRemove.add(col.name());
            }
        }
        table.addColumns(columnsToAdd.toArray(new Column<?>[0]));
        table.removeColumns(columnsToRemove.toArray(new String[0]));

        return table;
    }
}
