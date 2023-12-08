package hexlet.code;

import hexlet.code.shemas.StringSchema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringTest {
    private static StringSchema schema;
    private static Validator validator;

    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
        schema = validator.string();
    }

    @Test
    public void validTest() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    public void minLengthTest() {
        schema.minLength(5);
        assertTrue(schema.isValid("hello"));
        assertFalse(schema.isValid("what"));
    }

    @Test
    public void containTest() {
        schema.contains("wh");
        assertTrue(schema.isValid("what"));
        assertFalse(schema.isValid("wat"));
    }
}
