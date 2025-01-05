package Knowledge.WebApp.service;

import Knowledge.WebApp.dto.CustomerDTO;
import Knowledge.WebApp.mapper.CustomerMapper;
import Knowledge.WebApp.model.Customer;
import Knowledge.WebApp.model.Store;
import Knowledge.WebApp.repository.CustomerRepository;
import Knowledge.WebApp.repository.StoreRepository;
import Knowledge.WebApp.service.exceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, StoreRepository storeRepository) {
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
    }

//    @Transactional
//    @Override
//    public Customer insertCustomer(CustomerDTO customerDTO) throws Exception {
//
//        Customer customer;
//        try {
//            Store store = storeRepository.findById(customerDTO.getStoreId())
//                    .orElseThrow(() -> new Exception("Store not found"));
//            customer = customerRepository.save(CustomerMapper.CustomerToEntity(customerDTO));
//            if (customer.getId() == null) {
//                throw new Exception("Insert Error");
//            }
//            logger.info("Successfully inserted customer with ID: {}", customer.getId());
//        } catch (Exception e) {
//            logger.error("Error inserting customer: {}", e.getMessage(), e);
//            throw e;
//        }
//        return customer;
//    }

    @Transactional
    @Override
    public Customer insertCustomer(CustomerDTO customerDTO) throws Exception {
        Customer customer;
        try {

            if (customerDTO.getStoreId() == null) {
                throw new IllegalArgumentException("Store ID must not be null");
            }
            Store store = storeRepository.findById(customerDTO.getStoreId())
                    .orElseThrow(() -> new Exception("Store not found"));

            customer = customerRepository.save(CustomerMapper.CustomerToEntity(customerDTO, store));

            if (customer.getId() == null) {
                throw new Exception("Insert Error");
            }

            logger.info("Successfully inserted customer with ID: {}", customer.getId());
        } catch (Exception e) {
            logger.error("Error inserting customer: {}", e.getMessage(), e);
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
            if (customer == null) {
                throw new EntityNotFoundException(Customer.class, customerDTO.getId());
            }
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer.setVatNumber(customerDTO.getVatNumber());
            customer = customerRepository.save(customer);
            logger.info("Successfully updated customer with ID: {}", customer.getId());
        } catch (EntityNotFoundException e) {
            logger.error("Error updating customer: {}", e.getMessage(), e);
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
            if (customer == null) {
                throw new EntityNotFoundException(Customer.class, id);
            }
            customerRepository.deleteById(id);
            logger.info("Successfully deleted customer with ID: {}", id);
        } catch (EntityNotFoundException e) {
            logger.error("Error deleting customer: {}", e.getMessage(), e);
            throw e;
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        logger.info("Successfully retrieved all customers. Count: {}", customers.size());
        return customers;
    }

    @Override
    public List<Customer> getCustomersByStore(Long storeId) throws EntityNotFoundException {
        List<Customer> customers;
        try {
            Store store = storeRepository.findStoreById(storeId);
            if (store == null) {
                throw new EntityNotFoundException(Store.class, storeId);
            }
            customers = customerRepository.findByStore(store);
            if (customers.isEmpty()) {
                logger.info("No customers found for store ID: {}", storeId);
            } else {
                logger.info("Successfully retrieved customers for store ID: {}. Count: {}", storeId, customers.size());
            }
        } catch (EntityNotFoundException e) {
            logger.error("Error retrieving customers for store ID {}: {}", storeId, e.getMessage(), e);
            throw e;
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(Long id) throws EntityNotFoundException {
        Customer customer;
        try {
            customer = customerRepository.findCustomerById(id);
            if (customer == null) {
                throw new EntityNotFoundException(Customer.class, id);
            }
            logger.info("Successfully retrieved customer with ID: {}", id);
        } catch (EntityNotFoundException e) {
            logger.error("Error retrieving customer: {}", e.getMessage(), e);
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
                throw new EntityNotFoundException(Customer.class, 0L);  // Or better handle this case
            }
            logger.info("Successfully retrieved customer with VAT number: {}", vatNumber);
        } catch (EntityNotFoundException e) {
            logger.error("Error retrieving customer with VAT number {}: {}", vatNumber, e.getMessage(), e);
            throw e;
        }
        return customer;
    }
}
