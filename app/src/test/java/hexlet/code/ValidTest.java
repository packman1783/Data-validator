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
    public void beforeEach() {
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
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(mapSchema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "");
        human2.put("age", -5);
        assertFalse(mapSchema.isValid(human2));
    }

    @Test
    public void sizeTest() {
        data.put("name", validator.string().required());
        data.put("age", validator.number().positive());

        mapSchema.shape(data);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");

        mapSchema.sizeof(2);

        assertFalse(mapSchema.isValid(human1));
        human1.put("age", 100);
        assertTrue(mapSchema.isValid(human1));
    }

    @Test
    public void validNumberTest() {
        assertTrue(numberSchema.isValid(null));
        numberSchema.required();
        assertFalse(numberSchema.isValid(null));
    }

    @Test
    public void rangeTest() {
        numberSchema.range(2, 10);
        assertTrue(numberSchema.isValid(8));
        assertFalse(numberSchema.isValid(20));
    }

    @Test
    public void positiveTest() {
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
    }

    @Test
    public void minLengthTest() {
        stringSchema.minLength(5);
        assertTrue(stringSchema.isValid("hello"));
        assertFalse(stringSchema.isValid("what"));
    }

    @Test
    public void containTest() {
        stringSchema.contains("wh");
        assertTrue(stringSchema.isValid("what"));
        assertFalse(stringSchema.isValid("wat"));
    }
}
