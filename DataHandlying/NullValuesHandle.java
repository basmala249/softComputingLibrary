package DataHandlying;

import tech.tablesaw.api.Table;

import java.util.HashMap;
import java.util.Map;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.StringColumn;
public class NullValuesHandle {
    Table table;
    public NullValuesHandle(Table table) {
        this.table = table;
    }
    
    public Table handleNullValues() {
        for (var col : table.columns()) {
            if (col.countMissing() > 0) {
                switch (col) {
                    case DoubleColumn dc -> {
                        double q1 = dc.percentile(25);
                        double q3 = dc.percentile(75);
                        double iqr = q3 - q1;
                        double lower = q1 - 1.5 * iqr;
                        double upper = q3 + 1.5 * iqr;
                        
                        double median = dc.median();
                        double mean = dc.mean();
                        boolean hasOutlier = false;
                        
                        for (int i = 0; i < dc.size(); i++) {
                            if((dc.isMissing(i)))continue;
                            double val = dc.getDouble(i);
                            if (val < lower || val > upper) {
                                hasOutlier = true;
                                break;
                            }
                        }
                        
                        double fillValue = hasOutlier ? median : mean;
                        for (int i = 0; i < dc.size(); i++) {
                            if (dc.isMissing(i)) {
                                dc.set(i, fillValue);
                            }
                        }
                    }
                    case IntColumn ic -> {
                        double q1 = ic.percentile(25);
                        double q3 = ic.percentile(75);
                        double iqr = q3 - q1;
                        double lower = q1 - 1.5 * iqr;
                        double upper = q3 + 1.5 * iqr;
                        
                        int median = (int) Math.round(ic.median());
                        int mean = (int) Math.round(ic.mean());
                        boolean hasOutlier = false;
                        
                        for (int i = 0; i < ic.size(); i++) {
                            if((ic.isMissing(i)))continue;
                            int val = ic.get(i);
                            if ((val < lower || val > upper)) {
                                hasOutlier = true;
                                break;
                            }
                        }
                        
                        int fillValue = hasOutlier ? median : mean;
                        for (int i = 0; i < ic.size(); i++) {
                            if (ic.isMissing(i)) {
                                ic.set(i, fillValue);
                            }
                        }
                    }
                    case StringColumn sc -> {
                        Map<String, Integer> counts = new HashMap<>();
                        for (int i = 0; i < sc.size(); i++) {
                            if((sc.isMissing(i)))continue;
                            String val = sc.get(i);
                            if (val != null && !val.isEmpty()) {
                                counts.put(val, counts.getOrDefault(val, 0) + 1);
                            }
                        }
                        
                        String mode = counts.entrySet().stream()
                                .max(Map.Entry.comparingByValue())
                                .get()
                                .getKey();
                        
                        for (int i = 0; i < sc.size(); i++) {
                            if (sc.isMissing(i)) {
                                sc.set(i, mode);
                            }
                        }
                        
                    }
                    default -> {
                    }
                }
        }
    }
        return table;
    }
}
