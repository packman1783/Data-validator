### Hexlet tests and linter status:
[![Actions Status](https://github.com/packman1783/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/packman1783/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/4f4ea7ea608073bed3ed/maintainability)](https://codeclimate.com/github/packman1783/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/4f4ea7ea608073bed3ed/test_coverage)](https://codeclimate.com/github/packman1783/java-project-78/test_coverage)

## Description:
Data validator is a library that can be used to check the correctness of strings, numbers and maps, according to customizable rules for each data type.

## Use:
You need to create an object of this program.
Create and configure a data validation scheme that will contain verification rules.

Calling the string() method creates a StringSchema, it needs to be configured by adding constraints using the methods:
 * required() - does not allow null or empty string
 * minLength() - the string must be equal to or longer than the specified number
 * contains() - the string must contain a specific substring

for example: 
```
Validator v = new Validator();
StringSchema schema = v.string().required().minLength(5).contains("hex");
schema.isValid("what does the fox say");
```

Calling the number() method defines the NumberSchema. This schema is used for number validation with methods:
 * required() - does not allow null
 * positive() — the sign of the number must be positive
 * range() - adds a valid range that the number value must fall within, including boundaries

for example:
```
Validator v = new Validator();
NumberSchema schema = v.number().required().positive().range(-5, 5);
schema.isValid(10);
```

Calling the map() method defines a MapSchema. This schema is used to validate Map objects:
 * required() - does not allow null, Map data type required
 * sizeof() - the number of key-value pairs in the Map object must be equal to the specified one
 * shape() - allows you to describe validation for the values of each key of a Map object

for example:
```
Validator v = new Validator();
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());

schema.shape(schemas);

Map<String, Object> human = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);

schema.sizeof(2);

schema.isValid(human);
```
