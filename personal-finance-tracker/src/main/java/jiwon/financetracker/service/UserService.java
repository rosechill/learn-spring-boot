package jiwon.financetracker.service;

import jiwon.financetracker.dto.request.CreateUserRequest;
import jiwon.financetracker.dto.request.UpdateUserRequest;
import jiwon.financetracker.dto.response.UserResponse;
import jiwon.financetracker.entity.User;
import jiwon.financetracker.enums.Role;
import jiwon.financetracker.repository.UserRepository;
import jiwon.financetracker.security.BCrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        boolean existsByEmail = userRepository.existsByEmail(request.email());

        if (existsByEmail) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already registered");
        }

        User user = new User();
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPassword(BCrypt.hashpw(request.password(), BCrypt.gensalt()));
        user.setActive(true);
        user.setRole(Role.USER);

        userRepository.save(user);

        return new UserResponse(
                user.getFullName(),
                user.getEmail()
        );
    }

    @Transactional
    public UserResponse updateUser(UpdateUserRequest request) {
        boolean existsByEmail = userRepository.existsByEmail(request.email());

        if (!existsByEmail) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        User user = new User();

        if (request.fullName() != null) {
            user.setFullName(request.fullName());
        }

        if (request.password() != null) {
            user.setPassword(
                    BCrypt.hashpw(request.password(), BCrypt.gensalt())
            );
        }

        if (request.isActive() != null) {
            user.setEmail(request.email());
        }

        if (request.profilePicture() != null) {
            user.setProfilePicture(request.profilePicture());
        }

        if (request.password() != null) {
            user.setPassword(request.password());
        }

        userRepository.save(user);

        return new UserResponse(
                user.getFullName(),
                user.getEmail()
        );
    }
}
