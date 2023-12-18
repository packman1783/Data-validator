package hexlet.code.shemas;

public class NumberSchema extends BaseSchema {
    public NumberSchema() {
        super();
        ValidationRule integerRule = input -> {
            if (input == null || input instanceof Integer) {
                return true;
            }
            return false;
        };
        addRules("integer", integerRule);
    }

    public NumberSchema required() {
        ValidationRule requiredRule = input -> input != null;
        addRules("required", requiredRule);
        return this;
    }

    public NumberSchema positive() {
        ValidationRule positiveRule = input -> {
            if (input instanceof Integer && (Integer) input > 0) {
                return true;
            }
            return false;
        };
        addRules("positive", positiveRule);
        return this;
    }

    public NumberSchema range(int minNum, int maxNum) {
        ValidationRule rangeRule = input -> {
            if (input instanceof Integer && (Integer) input >= minNum && (Integer) input <= maxNum) {
                return true;
            }
            return false;
        };
        addRules("range", rangeRule);
        return this;
    }
}
