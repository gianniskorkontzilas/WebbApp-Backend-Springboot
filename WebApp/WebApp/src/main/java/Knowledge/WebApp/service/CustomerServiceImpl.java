package Knowledge.WebApp.service;

import Knowledge.WebApp.dto.CustomerDTO;
import Knowledge.WebApp.mapper.CustomerMapper;
import Knowledge.WebApp.model.Customer;
import Knowledge.WebApp.model.Store;
import Knowledge.WebApp.repository.CustomerRepository;
import Knowledge.WebApp.repository.StoreRepository;
import Knowledge.WebApp.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, StoreRepository storeRepository) {
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
    }

    @Transactional
    @Override
    public Customer insertCustomer(CustomerDTO customerDTO) throws Exception {
        Customer customer;

        try {
            customer = customerRepository.save( CustomerMapper.CustomerToEntity(customerDTO));
            if (customer.getId() == null) {
                throw new Exception("Insert Error");
            }
            System.out.println ("Successfully inserted customer with ID: "+ customer.getId());
        } catch (Exception e) {
            System.out.println("Error inserting customer: " + e.getMessage());
            throw e;
        }
        return customer;
    }

    @Transactional
    @Override
    public Customer updateCustomer(CustomerDTO customerDTO) throws EntityNotFoundException {
        Customer customer;
        try {
            customer = customerRepository.findCustomerById(customerDTO.getId());
            if (customer == null) throw new EntityNotFoundException(Customer.class, customerDTO.getId());
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer.setVatNumber(customerDTO.getVatNumber());
            customer = customerRepository.save(customer);
            System.out.println ("Successfully updated customer with ID: " + customer.getId());
        } catch (EntityNotFoundException e) {
            System.out.println("Error updating customer: " + e.getMessage());
            throw e;
        }
        return customer;
    }

    @Transactional
    @Override
    public Customer deleteCustomer(Long id) throws EntityNotFoundException {
        Customer customer;
        try {
            customer = customerRepository.findCustomerById(id);
            if (customer == null) throw new EntityNotFoundException(Customer.class, id);
            customerRepository.deleteById(id);
            System.out.println ("Successfully deleted customer with ID: " + id);
        } catch (EntityNotFoundException e) {
            System.out.println ("Error deleting customer: " + e.getMessage());
            throw e;
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        System.out.println("Retrieved all customers. Count: " + customers.size());
        return customers;
    }

    @Override
    public List<Customer> getCustomersByStore(Long storeId) throws EntityNotFoundException {
        List<Customer> customers;
        try {
            Store store = storeRepository.findStoreById(storeId);
            if (store == null) throw new EntityNotFoundException(Store.class, storeId);
            customers = customerRepository.findByStore(store);
            if (customers.isEmpty()) {
                System.out.println("No customers found for store ID: " + storeId);
            } else {
                System.out.println("Retrieved customers for store ID:  Count: "+ storeId + customers.size());
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Error retrieving customers for store ID: " +  storeId + e);
            throw e;
        }
        return customers;
    }




    @Override
    public Customer getCustomerById(Long id) throws EntityNotFoundException {
        Customer customer;
        try {
            customer = customerRepository.findCustomerById(id);
            if(customer == null) throw new EntityNotFoundException(Customer.class, id);
            System.out.println("Successfully retrieved customer with ID: " + id);
        } catch (EntityNotFoundException e) {
            System.out.println("Error retrieving customer: " + e.getMessage());
            throw e;
        }
        return customer;
    }

    @Override
    public Customer getCustomerByVatNumber(String vatNumber) throws EntityNotFoundException {
        Customer customer;
        try {
            customer = customerRepository.findByVatNumber(vatNumber);
            if (customer == null) {
                throw new EntityNotFoundException(Customer.class, 0L);
            }
            System.out.println("Successfully retrieved customer with VAT number: " + vatNumber);
        } catch (EntityNotFoundException e) {
            System.out.println("Error retrieving customer: " + e.getMessage());
            throw e;
        }
        return customer;
    }

}
