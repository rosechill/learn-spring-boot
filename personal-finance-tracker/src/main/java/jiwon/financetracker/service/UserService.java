package jiwon.financetracker.service;

import jiwon.financetracker.dto.request.CreateUserRequest;
import jiwon.financetracker.dto.request.UpdateUserRequest;
import jiwon.financetracker.dto.response.UserDetailResponse;
import jiwon.financetracker.dto.response.UserResponse;
import jiwon.financetracker.entity.User;
import jiwon.financetracker.enums.Role;
import jiwon.financetracker.repository.UserRepository;
import jiwon.financetracker.security.BCrypt;
import jiwon.financetracker.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Consumer;

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

    private <T> void updateIfNotNull(T value, Consumer<T> action) {
        if (value != null) {
            action.accept(value);
        }
    }

    @Transactional
    public UserResponse updateUser(UpdateUserRequest request, UserPrincipal principal) {
        User user = principal.getUser();

        updateIfNotNull(request.fullName(), user::setFullName);
        updateIfNotNull(request.email(), user::setEmail);
        updateIfNotNull(request.profilePicture(), user::setProfilePicture);
        updateIfNotNull(request.isActive(), user::setActive);
        updateIfNotNull(request.password(),
                password -> user.setPassword(
                        BCrypt.hashpw(password, BCrypt.gensalt())
                )
        );

        userRepository.save(user);

        return new UserResponse(
                user.getFullName(),
                user.getEmail()
        );
    }

    public UserDetailResponse getUserDetail(UserPrincipal principal) {
        User user = principal.getUser();

        return new UserDetailResponse(
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.isActive(),
                user.getProfilePicture()
        );
    }
}
