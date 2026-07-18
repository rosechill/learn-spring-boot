package jiwon.financetracker.service;

import jiwon.financetracker.dto.request.LoginUserRequest;
import jiwon.financetracker.dto.request.RefreshTokenRequest;
import jiwon.financetracker.dto.response.TokenResponse;
import jiwon.financetracker.entity.RefreshToken;
import jiwon.financetracker.entity.User;
import jiwon.financetracker.repository.TokenRepository;
import jiwon.financetracker.repository.UserRepository;
import jiwon.financetracker.security.BCrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;

    @Transactional
    public TokenResponse login(LoginUserRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                "Username or password is incorrect"
                        ));

        if (!BCrypt.checkpw(request.password(), user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Username or password is incorrect"
            );
        }

        String accessToken = jwtService.generateAccessToken(user);

        RefreshToken refreshToken = tokenRepository.findByUserId(user.getId())
                .orElseGet(RefreshToken::new);

        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiresAt(LocalDateTime.now().plusDays(7));

        tokenRepository.save(refreshToken);

        return new TokenResponse(
                accessToken,
                refreshToken.getToken()
        );
    }

    @Transactional
    public TokenResponse refresh(RefreshTokenRequest request) {
        RefreshToken token = tokenRepository.findByToken(request.refreshToken())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                "Invalid refresh token"
                        )
                );

        User user = token.getUser();

        boolean isExpired = token.getExpiresAt().isBefore(LocalDateTime.now());
        boolean isUnactive = !user.isActive();

        if (isExpired || isUnactive) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
        }

        String accessToken = jwtService.generateAccessToken(user);

        token.setToken(UUID.randomUUID().toString());
        token.setExpiresAt(LocalDateTime.now().plusDays(7));
        tokenRepository.save(token);

        return new TokenResponse(
                accessToken,
                token.getToken()
        );
    }

}
