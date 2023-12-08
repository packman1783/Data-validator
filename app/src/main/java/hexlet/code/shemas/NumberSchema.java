package hexlet.code.shemas;

public class NumberSchema {
    private boolean required;
    private boolean positive;
    private int minNumber;
    private int maxNumber;

    public NumberSchema required() {
        this.required = true;
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int minNum, int maxNum) {
        this.minNumber = minNum;
        this.maxNumber = maxNum;
        return this;
    }

    public boolean isValid(int number) {
        if (required && number == 0) {
            return false;
        }
        if (positive && number <= 0 ) {
            return false;
        }
        if (number < minNumber || number > maxNumber) {
            return false;
        }

        return true;
    }
}
