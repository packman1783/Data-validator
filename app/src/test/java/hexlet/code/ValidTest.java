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
    private static final MapSchema MAP_SCHEMA = new Validator().map();
    private static final NumberSchema NUMBER_SCHEMA = new Validator().number();
    private static final StringSchema STRING_SCHEMA = new Validator().string();
    private final Map<String, BaseSchema<String>> data = new HashMap<>();

    @Test
    public void validMapTest() {
        data.put("firstName", new Validator().string().required());
        data.put("lastName", new Validator().string().required().minLength(2));

        MAP_SCHEMA.shape(data);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(MAP_SCHEMA.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(MAP_SCHEMA.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(MAP_SCHEMA.isValid(human3));
    }

    @Test
    public void validNumberTest() {
        assertTrue(NUMBER_SCHEMA.isValid(null));
        NUMBER_SCHEMA.required();
        assertFalse(NUMBER_SCHEMA.isValid(null));

        NUMBER_SCHEMA.range(2, 10);
        assertTrue(NUMBER_SCHEMA.isValid(8));
        assertFalse(NUMBER_SCHEMA.isValid(20));

        NUMBER_SCHEMA.positive();
        assertTrue(NUMBER_SCHEMA.isValid(8));
        assertFalse(NUMBER_SCHEMA.isValid(-5));
    }

    @Test
    public void validStringTest() {
        assertTrue(STRING_SCHEMA.isValid(null));
        assertTrue(STRING_SCHEMA.isValid(""));
        STRING_SCHEMA.required();
        assertFalse(STRING_SCHEMA.isValid(null));
        assertFalse(STRING_SCHEMA.isValid(""));

        STRING_SCHEMA.contains("wh");
        assertTrue(STRING_SCHEMA.isValid("what"));
        assertFalse(STRING_SCHEMA.isValid("wat"));

        STRING_SCHEMA.minLength(5);
        assertTrue(STRING_SCHEMA.isValid("what does the fox say"));
        assertFalse(STRING_SCHEMA.isValid("what"));
    }
}
