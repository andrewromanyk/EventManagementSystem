package ua.edu.ukma.event_management_system.domain;

import lombok.Data;

@Data
public class User {
    private int id;
    private UserRole userRole;
    private String username;
    private String firstName;
    private String lastName;
    private int age;
}
