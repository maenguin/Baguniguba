package suheee.baguniguba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suheee.baguniguba.domain.Account;
import suheee.baguniguba.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByBarcode(String barcode);
    Optional<Product> findByStoreCodeAndBarcode(String storeCode, String barcode);
    List<Product> findAllByStore_Code(String storeCode);
}
