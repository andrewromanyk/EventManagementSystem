package ua.edu.ukma.event_management_system.exceptions.handler;

import ua.edu.ukma.event_management_system.exceptions.IllegalNameException;

public class NameValidator {

    private static final String FORBIDDEN_CHARACTERS = "[ыэёъ]";

    public static void validateName(String name) throws IllegalNameException {
        if (name == null || name.isEmpty()) {
            throw new IllegalNameException("Name cannot be null or empty.");
        }

        if (name.matches(".*" + FORBIDDEN_CHARACTERS + ".*")) {
            throw new IllegalNameException("Name contains forbidden characters: ы, э, ё, ъ.");
        }
    }
}