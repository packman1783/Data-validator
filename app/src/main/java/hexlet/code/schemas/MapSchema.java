package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema() {
        addRules("map", input -> input == null || input instanceof Map);
    }

    public MapSchema required() {
        addRules("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        addRules("sizeOf", input -> input instanceof Map && ((Map<?, ?>) input).size() == size);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> shape) {
        addRules("shape", input -> input instanceof Map && shape.entrySet().stream()
                .allMatch(entry -> {
                    String key = entry.getKey();
                    BaseSchema<T> value = entry.getValue();
                    return ((Map<?, ?>) input).containsKey(key) && value.isValid(((Map<?, ?>) input).get(key));
                }));
        return this;
    }
}
