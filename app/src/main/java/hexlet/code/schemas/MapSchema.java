package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
    public MapSchema() {
        super();
        Predicate<Object> mapRule = input -> input == null || input instanceof Map;
        addRules("map", mapRule);
    }

    public MapSchema required() {
        Predicate<Object> requiredRule = Objects::nonNull;
        addRules("required", requiredRule);

        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Object> sizeOfRule = input -> input != null && ((Map<?, ?>) input).size() == size;
        addRules("sizeOf", sizeOfRule);

        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> shape) {
        Predicate<Object> shapeRule = input -> {
            for (Map.Entry<String, BaseSchema> entry : shape.entrySet()) {
                String key = entry.getKey();
                BaseSchema value = entry.getValue();
                if (!((Map<?, ?>) input).containsKey(key) || !value.isValid(((Map<?, ?>) input).get(key))) {
                    return false;
                }
            }
            return true;
        };
        addRules("shape", shapeRule);

        return this;
    }
}
