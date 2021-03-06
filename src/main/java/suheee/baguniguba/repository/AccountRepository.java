package suheee.baguniguba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suheee.baguniguba.domain.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findByEmail(String email);
}
