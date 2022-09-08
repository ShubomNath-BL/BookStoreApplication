package com.example.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$", message = "Invalid First Name")
    private String firstName;
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$", message = "Invalid Last Name")
    private String lastName;
    @NotBlank(message = "email should not be blank")
    private String email;
    @NotBlank(message = "address should not be blank")
    private String address;
//    @NotBlank(message = "DOB should not be blank")
    private LocalDate dob;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Invalid password")
    private String password;
}
