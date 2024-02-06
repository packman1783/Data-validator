package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema<V extends BaseSchema> extends BaseSchema {
    public MapSchema() {
        Predicate<Object> mapRule = input -> input == null || input instanceof Map;
        addRules("map", mapRule);
    }

    public MapSchema<V> required() {
        Predicate<Object> requiredRule = Objects::nonNull;
        addRules("required", requiredRule);

        return this;
    }

    public MapSchema<V> sizeof(int size) {
        Predicate<Object> sizeOfRule = input -> ((Map<?, ?>) input).size() == size;
        addRules("sizeOf", sizeOfRule);

        return this;
    }

    public MapSchema<V> shape(Map<String, V> shape) {
        Predicate<Object> shapeRule = input -> shape.entrySet().stream()
                .allMatch(entry -> {
                    String key = entry.getKey();
                    V value = entry.getValue();
                    return ((Map<?, ?>) input).containsKey(key) && value.isValid(((Map<?, ?>) input).get(key));
                });
        addRules("shape", shapeRule);

        return this;
    }
}
