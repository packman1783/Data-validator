package hexlet.code.shemas;

public class StringSchema extends BaseSchema {
    public StringSchema() {
        super();
        Object statement = input -> {
            if (input == null || input instanceof String) {
                return true;
            }
        };
        addRules("string", statement);
    }

    public StringSchema required() {
        Object statement = input -> {
            if(input != null && !input.equals("")) {
                return true;
            }
        };
        addRules("required", statement);
        return this;
    }

    public StringSchema minLength(int length) {
        Object statement = input -> {
            if(input != null && ((String) input).length() >= length) {
                return true;
            }
        };
        addRules("minLength", statement);
        return this;
    }

    public StringSchema contains(String substring) {
        Object statement = input -> {
            if (input != null && ((String) input).contains(substring)) {
                return true;
            }
        };
        addRules("contains", statement);
        return this;
    }
}
