package jiwon.financetracker.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(

        @Size(min = 1, max = 100)
        String fullName,

        @Email
        @Size(min = 1, max = 255)
        String email,

        @Size(min = 5, max = 100)
        String password,

        Boolean isActive,

        @Size(min = 1, max = 100)
        String profilePicture

) {
}

