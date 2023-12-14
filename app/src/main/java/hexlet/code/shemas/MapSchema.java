package hexlet.code.shemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    public MapSchema() {
        super();
        Object statement = input -> {
            if (input == null || input instanceof Map) {
                return true;
            }
        };
        addRules("map", statement);
    }

    public MapSchema required() {
        Object statement = input -> input != null;
        addRules("required", statement);
        return this;
    }

    public MapSchema sizeOf(int size) {
        Object statement = input -> {
            if (input != null && ((Map) input).size() == size) {
                return true;
            }
        };
        addRules("sizeOf", statement);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> shape) {
        for (Map.Entry<String, BaseSchema> entry : shape.entrySet()) {
            String key = entry.getKey();
            BaseSchema value = entry.getValue();
            Object statement = input -> {
                if (!((Map) input).containsKey(key) || !value.isValid(((Map) input).get(key))) {
                    return false;
                }
            };
            addRules("shape", statement);
        }
        return this;
    }
}
