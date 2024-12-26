package Knowledge.WebApp.repository;

import Knowledge.WebApp.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository <Store, Long> {


    Store findStoreById(Long id);
}
