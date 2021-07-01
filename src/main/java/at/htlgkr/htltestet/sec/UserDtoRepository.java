package at.htlgkr.htltestet.sec;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserDtoRepository extends JpaRepository<UserDto, String> {
    Optional<UserDto> findUserByUsername(String username);
}
