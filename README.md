### Hexlet tests and linter status:
[![Actions Status](https://github.com/packman1783/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/packman1783/java-project-78/actions)

## Description:
Data validator is a library that can be used to check the correctness of strings, numbers and maps, according to customizable rules for each data type.

## Use:
Create and configure a data validation scheme that will contain verification rules.

**Calling the string() method creates a StringSchema, using the methods:**
 * required() - does not allow null or empty string
 * minLength() - set the minimum required length for a string
 * contains() - the string must contain a specific substring

for example: 
```
Validator v = new Validator();
StringSchema schema = v.string().required().minLength(5).contains("hex");
schema.isValid("what does the fox say");
```

**Calling the number() method defines the NumberSchema, with methods:**
 * required() - does not allow null
 * positive() — the number must be positive
 * range() - adds a range that the number must fall within, including boundaries

for example:
```
Validator v = new Validator();
NumberSchema schema = v.number().required().positive().range(-5, 5);
schema.isValid(10);
```

**Calling the map() method defines a MapSchema, to validate Map objects:**
 * required() - does not allow null, Map data type required
 * sizeof() - the number of key-value pairs in the Map object must be equal
 * shape() - allows you to describe validation for the values of each key of a Map object

for example:
```
Validator v = new Validator();
Map<String, BaseSchema<String>> schemas = new HashMap<>();
schemas.put("firstName", v.string().required());
schemas.put("lastName", v.string().required().minLength(2));

schema.shape(schemas);

Map<String, String> human1 = new HashMap<>();
human1.put("firstName", "John");
human1.put("lastName", "Smith");
schema.isValid(human1);

Map<String, String> human2 = new HashMap<>();
human2.put("firstName", "John");
human2.put("lastName", null);
schema.isValid(human2);
```
