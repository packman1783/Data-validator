package hexlet.code.shemas;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseSchema {
    private final Map<String, Object> rules;

    public BaseSchema() {
        rules = new HashMap<>();
    }

    public void addRules(String name, Object statement) {
        rules.put(name, statement);
    }

    public boolean isValid(Object data);
}
