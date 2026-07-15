package jiwon.financetracker.controller;

import jakarta.validation.Valid;
import jiwon.financetracker.dto.request.CreateUserRequest;
import jiwon.financetracker.dto.request.UpdateUserRequest;
import jiwon.financetracker.dto.response.BaseResponse;
import jiwon.financetracker.dto.response.UserResponse;
import jiwon.financetracker.entity.User;
import jiwon.financetracker.security.UserPrincipal;
import jiwon.financetracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/api/test/me")
    public UserResponse me(
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        User user = principal.getUser();

        return new UserResponse(
                user.getFullName(),
                user.getEmail()
        );

    }

    @PostMapping(
            path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse<UserResponse>> register(@RequestBody @Valid CreateUserRequest request) {
        UserResponse user = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponse<>(
                        HttpStatus.CREATED.value(),
                        "User created successfully",
                        user)
                );
    }

    @PatchMapping(
            path = "/api/users/current",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse<UserResponse>> update(
            @RequestBody @Valid UpdateUserRequest request
    ) {
        UserResponse user = userService.updateUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponse<>(
                        HttpStatus.CREATED.value(),
                        "User updated successfully",
                        user)
                );
    }

}
