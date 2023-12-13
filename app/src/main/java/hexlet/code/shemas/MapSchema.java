package hexlet.code.shemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private Integer expectedSize = null;
    private Map<String, BaseSchema> map;

    public MapSchema sizeOf(int size) {
        this.expectedSize = size;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> shape) {
        this.map = shape;
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
        for (Map.Entry<String, BaseSchema> entry : map.entrySet()) {
            String key = entry.getKey();
            BaseSchema schema = entry.getValue();
            if (!((Map) data).containsKey(key) || !schema.isValid(((Map) data).get(key))) {
                return false;
            }
        }

        return true;
    }
}
