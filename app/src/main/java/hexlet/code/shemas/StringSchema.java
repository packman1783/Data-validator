package hexlet.code.shemas;

public class StringSchema extends BaseSchema {
    public StringSchema() {
        super();
        ValidationRule stringRule = input -> input == null || input instanceof String;
        addRules("string", stringRule);
    }

    public StringSchema required() {
        ValidationRule requiredRule = input -> input != null && !((String) input).isEmpty();
        addRules("required", requiredRule);

        return this;
    }

    public StringSchema minLength(int length) {
        ValidationRule minLangthRule = input -> input != null && ((String) input).length() >= length;
        addRules("minLength", minLangthRule);

        return this;
    }

    public StringSchema contains(String substring) {
        ValidationRule containsRule = input -> input != null && ((String) input).contains(substring);
        addRules("contains", containsRule);

        return this;
    }
}
