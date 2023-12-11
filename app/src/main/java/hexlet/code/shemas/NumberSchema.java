package hexlet.code.shemas;

public class NumberSchema extends BaseSchema {
    private boolean positive;
    private int minNumber;
    private int maxNumber;

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int minNum, int maxNum) {
        this.minNumber = minNum;
        this.maxNumber = maxNum;
        return this;
    }

    @Override
    public boolean isValid(Object data) {
        Integer number = (Integer) data;
        if (required) {
            if (number == null) {
                return false;
            }
        } else {
            if (number == null) {
                return true;
            }
        }
        if (positive && number > 0) {
            return true;
        }
        if (number < minNumber || number > maxNumber) {
            return false;
        }

        return true;
    }
}
