package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidTest {
    private static MapSchema mapSchema;
    private static NumberSchema numberSchema;
    private static StringSchema stringSchema;
    private static Validator validator;
    private Map<String, BaseSchema> data;

    @BeforeEach
    public final void beforeEach() {
        validator = new Validator();
        mapSchema = validator.map();
        numberSchema = validator.number();
        stringSchema = validator.string();
        data = new HashMap<>();
    }

    @Test
    public void validMapTest() {
        data.put("name", validator.string().required());
        data.put("age", validator.number().positive());

        mapSchema.shape(data);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "");
        human1.put("age", -5);
        assertFalse(mapSchema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Kolya");

        mapSchema.sizeof(2);

        assertFalse(mapSchema.isValid(human2));
        human2.put("age", 100);
        assertTrue(mapSchema.isValid(human2));
    }

    @Test
    public void validNumberTest() {
        assertTrue(numberSchema.isValid(null));
        numberSchema.required();
        assertFalse(numberSchema.isValid(null));

        numberSchema.range(2, 10);
        assertTrue(numberSchema.isValid(8));
        assertFalse(numberSchema.isValid(20));

        numberSchema.positive();
        assertTrue(numberSchema.isValid(8));
        assertFalse(numberSchema.isValid(-5));
    }

    @Test
    public void validStringTest() {
        assertTrue(stringSchema.isValid(null));
        assertTrue(stringSchema.isValid(""));
        stringSchema.required();
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(""));

        stringSchema.contains("wh");
        assertTrue(stringSchema.isValid("what"));
        assertFalse(stringSchema.isValid("wat"));

        stringSchema.minLength(5);
        assertTrue(stringSchema.isValid("what does the fox say"));
        assertFalse(stringSchema.isValid("what"));
    }
}
