package ua.edu.ukma.event_management_system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.edu.ukma.event_management_system.domain.UserRole;

@NoArgsConstructor
@Data
public class UserDto {
    private int id;
    private UserRole userRole;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int age;

    public UserDto(int id, UserRole userRole, String username, String firstName, String lastName, String email, String phoneNumber, int age) {
        this.id = id;
        this.userRole = userRole;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }
}
