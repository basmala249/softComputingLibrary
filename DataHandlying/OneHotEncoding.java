package DataHandlying;

import tech.tablesaw.api.Table;

public class OneHotEncoding {
    Table table;
    public OneHotEncoding(Table table) {
        this.table = table;
    }
    Table encode() {
        // Implementation of one-hot encoding
        return table;
    }
}
