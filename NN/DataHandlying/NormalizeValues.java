package NN.DataHandlying;

import tech.tablesaw.api.Table;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.IntColumn;

import java.util.HashMap;
import java.util.Map;

public class NormalizeValues {
    private Table table;
    private Map<String, Double> mins = new HashMap<>();
    private Map<String, Double> maxs = new HashMap<>();

    public NormalizeValues(Table table) {
        this.table = table;
        computeMinMax();
    }

    private void computeMinMax() {
        for (var col : table.columns()) {
            if (col instanceof DoubleColumn dc) {
                mins.put(col.name(), dc.min());
                maxs.put(col.name(), dc.max());
            } else if (col instanceof IntColumn ic) {
                mins.put(col.name(), (double) ic.min());
                maxs.put(col.name(), (double) ic.max());
            }
        }
    }

    public Table normalize() {
        return applyNormalization(table);
    }

    public Table apply(Table otherTable) {
        return applyNormalization(otherTable);
    }

    private Table applyNormalization(Table t) {
        for (var col : t.columns()) {
            String name = col.name();
            if (mins.containsKey(name)) {
                double min = mins.get(name);
                double max = maxs.get(name);
                if (max != min) {
                    if (col instanceof DoubleColumn dc) {
                        for (int i = 0; i < dc.size(); i++) {
                            dc.set(i, (dc.getDouble(i) - min) / (max - min));
                        }
                    } else if (col instanceof IntColumn ic) {
                        for (int i = 0; i < ic.size(); i++) {
                            double normalized = (ic.get(i) - min) / (max - min);
                            ic.set(i, (int) Math.round(normalized)); // Better than cast for 0/1
                        }
                    }
                } else {
                    // Set to 0 if constant
                    if (col instanceof DoubleColumn dc) {
                        for (int i = 0; i < dc.size(); i++) dc.set(i, 0.0);
                    } else if (col instanceof IntColumn ic) {
                        for (int i = 0; i < ic.size(); i++) ic.set(i, 0);
                    }
                }
            }
        }
        return t;
    }
}