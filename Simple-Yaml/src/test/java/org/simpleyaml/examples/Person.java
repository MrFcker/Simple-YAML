package org.simpleyaml.examples;

import org.simpleyaml.configuration.serialization.ConfigurationSerializable;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class that represents a Person that is used only for the test examples.<br>
 * Note that has methods to serialize and deserialize Person objects to save them later.
 *
 * @author Carlos Lazaro Costa
 */
public class Person implements ConfigurationSerializable {

    private final String dni;

    private final String name;

    private final int birthYear;

    private boolean isAlive;

    public Person(String dni, String name, int birthYear, boolean isAlive) {
        this.dni = dni;
        this.name = name;
        this.birthYear = birthYear;
        this.isAlive = isAlive;
    }

    public Person(String dni, String name, int birthYear) {
        this(dni, name, birthYear, true);
    }

    /*
     * The following methods allows you to serialize and deserialize
     * your object to save them correctly to a YAML file.
     *
     * If you want you can create a constructor that accepts a single Map<String, Object>
     * to deserialize instead using the method deserialize of below.
     */

    public static Person deserialize(Map<String, Object> mappedObject) { // note that is static
        return new Person((String) mappedObject.get("dni"),
            (String) mappedObject.get("name"),
            (int) mappedObject.get("birthYear"),
            (boolean) mappedObject.get("isAlive"));
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> mappedObject = new LinkedHashMap<String, Object>();
        mappedObject.put("dni", dni);
        mappedObject.put("name", name);
        mappedObject.put("birthYear", birthYear);
        mappedObject.put("isAlive", isAlive);
        return mappedObject;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
    }

    @Override
    public String toString() {
        return "Person [dni= " + dni + ", name= " + name + ", birthYear= " + birthYear + ", isAlive= " + isAlive + "]";
    }

}