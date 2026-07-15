package jiwon.financetracker.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(

        @NotBlank
        @Size(min = 1, max = 100)
        String fullName,

        @NotBlank
        @Email
        @Size(min = 1, max = 255)
        String email,

        @NotBlank
        @Size(min = 5, max = 100)
        String password

) {
}

