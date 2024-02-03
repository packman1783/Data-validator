package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {
    public MapSchema() {
        Predicate<Object> mapRule = input -> input == null || input instanceof Map;
        addRules("map", mapRule);
    }

    public MapSchema required() {
        Predicate<Object> requiredRule = Objects::nonNull;
        addRules("required", requiredRule);

        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Object> sizeOfRule = input -> ((Map<?, ?>) input).size() == size;
        addRules("sizeOf", sizeOfRule);

        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> shape) {
        Predicate<Object> shapeRule = input -> shape.entrySet().stream()
                .allMatch(entry -> {
                    String key = entry.getKey();
                    BaseSchema value = entry.getValue();
                    return ((Map<?, ?>) input).containsKey(key) && value.isValid(((Map<?, ?>) input).get(key));
                });
        addRules("shape", shapeRule);

        return this;
    }
}
