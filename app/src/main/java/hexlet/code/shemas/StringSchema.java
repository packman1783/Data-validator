package hexlet.code.shemas;

public class StringSchema {
    private boolean required;
    private int minLength;
    private String containsSubstring;

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.containsSubstring = substring;
        return this;
    }

    public boolean isValid(String text) {
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
