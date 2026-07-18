package jiwon.financetracker.dto.response;

import jiwon.financetracker.enums.Role;

public record UserDetailResponse(

        String fullName,

        String email,

        Role role,

        boolean isActive,

        String profilePicture

) {
}

