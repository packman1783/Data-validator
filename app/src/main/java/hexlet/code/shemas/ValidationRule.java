package hexlet.code.shemas;

@FunctionalInterface
public interface ValidationRule {
    boolean apply (Object data);
}
