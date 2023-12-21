package hexlet.code.schemas;

@FunctionalInterface
public interface ValidationRule {
    boolean apply(Object data);
}
