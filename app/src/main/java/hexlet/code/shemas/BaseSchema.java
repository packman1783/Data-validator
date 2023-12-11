package hexlet.code.shemas;

public abstract class BaseSchema {
    protected boolean required;

    public BaseSchema required() {
        this.required = true;
        return this;
    }

    public abstract boolean isValid(Object data);
}
