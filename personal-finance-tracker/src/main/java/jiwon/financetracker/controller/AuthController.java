package jiwon.financetracker.controller;

import jakarta.validation.Valid;
import jiwon.financetracker.dto.request.LoginUserRequest;
import jiwon.financetracker.dto.request.RefreshTokenRequest;
import jiwon.financetracker.dto.response.BaseResponse;
import jiwon.financetracker.dto.response.TokenResponse;
import jiwon.financetracker.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(
            path = "api/auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse<TokenResponse>> login(@RequestBody @Valid LoginUserRequest request) {
        TokenResponse token = authService.login(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Login Successful",
                        token)
                );
    }

    @PostMapping(
            path = "api/auth/refresh",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse<TokenResponse>> refresh(@RequestBody @Valid RefreshTokenRequest request) {
        TokenResponse token = authService.refresh(request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Refresh token Successful",
                        token)
                );
    }

}
