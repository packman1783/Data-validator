package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema {
    public NumberSchema() {
        super();
        ValidationRule integerRule = input -> input == null || input instanceof Integer;
        addRules("integer", integerRule);
    }

    public NumberSchema required() {
        ValidationRule requiredRule = Objects::nonNull;
        addRules("required", requiredRule);

        return this;
    }

    public NumberSchema positive() {
        ValidationRule positiveRule = input -> input instanceof Integer && (Integer) input > 0;
        addRules("positive", positiveRule);

        return this;
    }

    public NumberSchema range(int minNum, int maxNum) {
        ValidationRule rangeRule = input -> input instanceof Integer
                && (Integer) input >= minNum && (Integer) input <= maxNum;
        addRules("range", rangeRule);

        return this;
    }
}
