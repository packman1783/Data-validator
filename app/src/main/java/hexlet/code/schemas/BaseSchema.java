package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {
    private final Map<String, Predicate<Object>> rules;

    public BaseSchema() {
        rules = new HashMap<>();
    }

    public void addRules(String name, Predicate<Object> rule) {
        rules.put(name, rule);
    }

    public boolean isValid(Object data) {
        for (Map.Entry<String, Predicate<Object>> entry : rules.entrySet()) {
            Predicate<Object> rule = entry.getValue();
            if (!rule.test(data)) {
                return false;
            }
        }
        return true;
    }
}
