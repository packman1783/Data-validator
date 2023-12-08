package hexlet.code;

import hexlet.code.shemas.NumberSchema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberTest {
    private static NumberSchema schema;
    private static Validator validator;

    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
        schema = validator.number();
    }

    @Test
    public void validTest() {
        assertTrue(schema.isValid(null));
        schema.required();
        assertFalse(schema.isValid(null));

    }
    @Test
    public void rangeTest() {
        schema.range(2, 10);
        assertTrue(schema.isValid(8));
        assertFalse(schema.isValid(20));
    }


    @Test
    public void positiveTest() {
        schema.positive();
        assertTrue(schema.isValid(8));
        assertFalse(schema.isValid(-5));
    }
}
