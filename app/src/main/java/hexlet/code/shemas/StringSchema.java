package hexlet.code.shemas;

public class StringSchema extends BaseSchema {
    private int minLength;
    private String containsSubstring;

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.containsSubstring = substring;
        return this;
    }

    @Override
    public boolean isValid(Object data) {
        String text = (String) data;
        if (required && (text == null || text.isEmpty())) {
            return false;
        }
        if (text != null && text.length() < minLength) {
            return false;
        }
        if (containsSubstring != null && text != null && !text.contains(containsSubstring)) {
            return false;
        }
        return true;
    }
}
