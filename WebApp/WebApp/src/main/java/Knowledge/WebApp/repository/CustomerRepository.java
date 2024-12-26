package Knowledge.WebApp.repository;

import Knowledge.WebApp.model.Customer;
import Knowledge.WebApp.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByStore(Store store);
    Customer findCustomerById(Long id);

    Customer findByVatNumber(String vatNumber);

}
