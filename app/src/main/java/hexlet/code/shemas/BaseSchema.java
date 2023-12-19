package hexlet.code.shemas;

import java.util.HashMap;
import java.util.Map;

public class BaseSchema {
    private final Map<String, ValidationRule> rules;

    public BaseSchema() {
        rules = new HashMap<>();
    }

    public void addRules(String name, ValidationRule rule) {
        rules.put(name, rule);
    }

    public boolean isValid(Object data) {
        for (Map.Entry<String, ValidationRule> entry : rules.entrySet()) {
            ValidationRule rule = entry.getValue();
            if (!checkRule(rule, data)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRule(ValidationRule rule, Object data) {
        return rule.apply(data);
    }
}
