package suheee.baguniguba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suheee.baguniguba.domain.Account;
import suheee.baguniguba.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store,Long> {

    Optional<Store> findByCode(String code);
    Optional<Store> findByNameAndAccount_Email(String name, String accountID);
    List<Store> findByAccount_Email(String accountEmail);
}
