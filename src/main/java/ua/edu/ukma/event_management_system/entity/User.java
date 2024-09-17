package ua.edu.ukma.event_management_system.entity;

public class User {
    private int id;
    private UserRole userRole;
    private String username;
    private String firstName;
    private String lastName;
    private int age;

    public User(int id, UserRole userRole, String username, String firstName, String lastName, int age) {
        this.id = id;
        this.userRole = userRole;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
