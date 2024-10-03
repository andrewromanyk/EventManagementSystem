package ua.edu.ukma.event_management_system.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.edu.ukma.event_management_system.domain.UserRole;

@NoArgsConstructor
@Data
public class UserDto {
    private int id;
    private UserRole userRole;
    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "First name is required")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$")
    private String phoneNumber;

    @Min(value = 18, message = "Age should be at least 18")
    @Max(value = 120, message = "Age should be less than 120")
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
