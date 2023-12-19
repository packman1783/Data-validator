package hexlet.code.shemas;

public class StringSchema extends BaseSchema {
    public StringSchema() {
        super();
        ValidationRule stringRule = input -> {
            if (input == null || input instanceof String) {
                return true;
            }
            return false;
        };
        addRules("string", stringRule);
    }

    public StringSchema required() {
        ValidationRule requiredRule = input -> {
            if (input != null && !((String) input).isEmpty()) {
                return true;
            }
            return false;
        };
        addRules("required", requiredRule);
        return this;
    }

    public StringSchema minLength(int length) {
        ValidationRule minLangthRule = input -> {
            if (input != null && ((String) input).length() >= length) {
                return true;
            }
            return false;
        };
        addRules("minLength", minLangthRule);
        return this;
    }

    public StringSchema contains(String substring) {
        ValidationRule containsRule = input -> {
            if (input != null && ((String) input).contains(substring)) {
                return true;
            }
            return false;
        };
        addRules("contains", containsRule);
        return this;
    }
}
