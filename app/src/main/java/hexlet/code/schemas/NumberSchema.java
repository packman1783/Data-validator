package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema() {
        Predicate<Object> integerRule = input -> input == null || input instanceof Integer;
        addRules("integer", integerRule);
    }

    public NumberSchema required() {
        Predicate<Object> requiredRule = Objects::nonNull;
        addRules("required", requiredRule);

        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> positiveRule = input -> input instanceof Integer && (Integer) input > 0;
        addRules("positive", positiveRule);

        return this;
    }

    public NumberSchema range(int minNum, int maxNum) {
        Predicate<Object> rangeRule = input -> input instanceof Integer
                && (Integer) input >= minNum && (Integer) input <= maxNum;
        addRules("range", rangeRule);

        return this;
    }
}
