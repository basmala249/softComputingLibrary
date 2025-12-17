package DataHandlying;

import tech.tablesaw.api.Table;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.IntColumn;


public class NormalizeValues {
    Table table;
    public NormalizeValues(Table table) {
        this.table = table;
    }
    Table normalize() {
        for (var col : table.columns()) {
            switch (col) {
                case DoubleColumn dc -> {
                    double min = dc.min();
                    double max = dc.max();
                    if (max != min) {
                        for (int i = 0; i < dc.size(); i++) {
                            dc.set(i, (dc.getDouble(i) - min) / (max - min));
                        }
                    } else {
                        for (int i = 0; i < dc.size(); i++) {
                            dc.set(i, 0.0);
                        }
                    }
                }
                case IntColumn ic -> {
                    double min = ic.min();
                    double max = ic.max();
                    if (max != min) {
                        for (int i = 0; i < ic.size(); i++) {
                            double normalized = (ic.get(i) - min) / (max - min);
                            ic.set(i, (int) Math.round(normalized * 100));
                        }
                    } else {
                        for (int i = 0; i < ic.size(); i++) {
                            ic.set(i, 0);
                        }
                    }
                }
                default -> {
                }
            }
        }
        return table;
    }
}
