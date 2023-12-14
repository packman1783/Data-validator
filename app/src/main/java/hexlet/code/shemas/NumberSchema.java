package hexlet.code.shemas;

public class NumberSchema extends BaseSchema {
    public NumberSchema() {
        super();
        Object statement = input -> {
            if (input == null || input instanceof Integer) {
                return true;
            }
        };
        addRules("integer", statement);
    }

    public NumberSchema required() {
        Object statement = input -> input != null;
        addRules("required", statement);
        return this;
    }

    public NumberSchema positive() {
        Object statement = input -> {
            if (input instanceof Integer && (Integer) input > 0 {
                return true;
            }
        };
        addRules("positive", statement);
        return this;
    }

    public NumberSchema range(int minNum, int maxNum) {
        Object statement = input -> {
            if (input instanceof Integer && (Integer) input >= minNum && (Integer) input <= maxNum) {
                return true;
            }
        };
        addRules("range", statement);
        return this;
    }
}
