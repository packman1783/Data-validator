package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {
    private final Map<String, Predicate<Object>> rules = new HashMap<>();

    public final void addRules(String name, Predicate<Object> rule) {
        rules.put(name, rule);
    }

    public final boolean isValid(Object data) {
        if (data == null && !rules.containsKey("required")) {
            return true;
        }

        for (Map.Entry<String, Predicate<Object>> entry : rules.entrySet()) {
            Predicate<Object> rule = entry.getValue();
            if (!rule.test(data)) {
                return false;
            }
        }

        return true;
    }
}
