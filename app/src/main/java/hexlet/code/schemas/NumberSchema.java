package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema() {
        addRules("integer", input -> input == null || input instanceof Integer);
    }

    public NumberSchema required() {
        addRules("required", input -> input != null);
        return this;
    }

    public NumberSchema positive() {
        addRules("positive", input -> input instanceof Integer && (Integer) input > 0);
        return this;
    }

    public NumberSchema range(int minNum, int maxNum) {
        Predicate<Object> rangeRule = input -> input instanceof Integer
                && (Integer) input >= minNum && (Integer) input <= maxNum;
        addRules("range", rangeRule);
        return this;
    }
}
