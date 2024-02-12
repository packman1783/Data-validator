package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidTest {
    private static final MapSchema mapSchema = new Validator().map();
    private static final NumberSchema numberSchema = new Validator().number();
    private static final StringSchema stringSchema = new Validator().string();
    private final Map<String, BaseSchema<String>> data = new HashMap<>();

    @Test
    public void validMapTest() {
        data.put("firstName", new Validator().string().required());
        data.put("lastName", new Validator().string().required().minLength(2));

        mapSchema.shape(data);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(mapSchema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(mapSchema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(mapSchema.isValid(human3));
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
