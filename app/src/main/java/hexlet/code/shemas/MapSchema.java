package hexlet.code.shemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    public MapSchema() {
        super();
        ValidationRule mapRule = input -> input == null || input instanceof Map;
        addRules("map", mapRule);
    }

    public MapSchema required() {
        ValidationRule requiredRule = input -> input != null;
        addRules("required", requiredRule);

        return this;
    }

    public MapSchema sizeOf(int size) {
        ValidationRule sizeOfRule = input -> input != null && ((Map) input).size() == size;
        addRules("sizeOf", sizeOfRule);

        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> shape) {
        ValidationRule shapeRule = input -> {
            for (Map.Entry<String, BaseSchema> entry : shape.entrySet()) {
                String key = entry.getKey();
                BaseSchema value = entry.getValue();
                if (!((Map) input).containsKey(key) || !value.isValid(((Map) input).get(key))) {
                    return false;
                }
            }
            return true;
        };
        addRules("shape", shapeRule);

        return this;
    }
}
