package at.htlgkr.htltestet.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationDataRepository extends JpaRepository<RegistrationData, Long> {



}
