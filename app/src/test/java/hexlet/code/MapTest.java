package hexlet.code;

import hexlet.code.shemas.BaseSchema;
import hexlet.code.shemas.MapSchema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapTest {
    private static MapSchema schema;
    private static Validator validator;

    private Map<String, BaseSchema> data;

    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
        schema = validator.map();
        data = new HashMap<>();
    }

    @Test
    public void validTest() {
        data.put("name", validator.string().required());
        data.put("age", validator.number().positive());

        schema.shape(data);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "");
        human2.put("age", -5);
        assertFalse(schema.isValid(human2));
    }

    @Test
    public void sizeTest() {
        data.put("name", validator.string().required());
        data.put("age", validator.number().positive());

        schema.shape(data);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");

        schema.sizeOf(2);

        assertFalse(schema.isValid(human1));
        human1.put("age", 100);
        assertTrue(schema.isValid(human1));
    }
}
