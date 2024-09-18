package ua.edu.ukma.event_management_system.entity;

import ua.edu.ukma.event_management_system.service.interfaces.Ratable;

public class User implements Ratable {
    private int id;
    private UserRole userRole;
    private String username;
    private String firstName;
    private String lastName;
    private int age;
    private int rating;

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

    @Override
    public void rate(int rating) {
        if (rating < 1)
            this.rating = 1;
        else if(rating > 5)
            this.rating = 5;
        else
            this.rating = rating;
    }

    @Override
    public int getRating() {
        return rating;
    }
}
