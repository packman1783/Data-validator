package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    public StringSchema() {
        addRules("string", input -> input == null || input instanceof String);
    }

    public StringSchema required() {
        addRules("required", input -> input != null && !((String) input).isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addRules("minLength", input -> input == null || ((String) input).length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addRules("contains", input -> input != null && ((String) input).contains(substring));
        return this;
    }
}
