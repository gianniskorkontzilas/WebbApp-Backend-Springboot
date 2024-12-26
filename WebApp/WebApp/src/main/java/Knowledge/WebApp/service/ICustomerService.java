package Knowledge.WebApp.service;

import Knowledge.WebApp.dto.CustomerDTO;
import Knowledge.WebApp.model.Customer;
import Knowledge.WebApp.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface ICustomerService {
    Customer insertCustomer(CustomerDTO customerDTO) throws Exception;

    Customer updateCustomer(CustomerDTO customerDTO) throws EntityNotFoundException;

    Customer deleteCustomer(Long id) throws EntityNotFoundException;

    List<Customer> getAllCustomers();

    List<Customer> getCustomersByStore(Long storeId) throws EntityNotFoundException;

    Customer getCustomerById(Long id) throws EntityNotFoundException;

    Customer getCustomerByVatNumber(String vatNumber) throws EntityNotFoundException;
}
