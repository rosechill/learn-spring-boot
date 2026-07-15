package jiwon.financetracker.repository;

import jiwon.financetracker.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {

    RefreshToken findByToken(String token);

    Optional<RefreshToken> findByUserId(Long userId);

}
