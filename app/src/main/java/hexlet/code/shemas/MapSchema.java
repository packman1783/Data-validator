package hexlet.code.shemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private Integer expectedSize = null;

    public MapSchema sizeOf(int size) {
        this.expectedSize = size;
        return this;
    }

    @Override
    public boolean isValid(Object data) {
        if (data == null) {
            return !required;
        }
        if (required && ((Map) data).isEmpty()) {
            return false;
        }
        if (expectedSize != null && ((Map) data).size() != expectedSize) {
            return false;
        }

        return true;
    }
}
