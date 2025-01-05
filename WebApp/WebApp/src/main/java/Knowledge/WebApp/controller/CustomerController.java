package Knowledge.WebApp.controller;

import Knowledge.WebApp.dto.CustomerDTO;
import Knowledge.WebApp.model.Customer;
import Knowledge.WebApp.service.ICustomerService;
import Knowledge.WebApp.service.exceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> insertCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            Customer customer = customerService.insertCustomer(customerDTO);
            logger.info("Successfully inserted customer with ID: {}", customer.getId());
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error inserting customer: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        try {
            customerDTO.setId(id);
            Customer customer = customerService.updateCustomer(customerDTO);
            logger.info("Successfully updated customer with ID: {}", customer.getId());
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.error("Error updating customer with ID {}: {}", customerDTO.getId(), e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            logger.info("Successfully deleted customer with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            logger.error("Error deleting customer with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        logger.info("Retrieved all customers. Count: {}", customers.size());
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            logger.info("Successfully retrieved customer with ID: {}", id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.error("Customer with ID {} not found: {}", id, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vat/{vatNumber}")
    public ResponseEntity<Customer> getCustomerByVatNumber(@PathVariable String vatNumber) {
        try {
            Customer customer = customerService.getCustomerByVatNumber(vatNumber);
            logger.info("Successfully retrieved customer with VAT number: {}", vatNumber);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.error("Customer with VAT number {} not found: {}", vatNumber, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Customer>> getCustomersByStore(@PathVariable Long storeId) {
        try {
            List<Customer> customers = customerService.getCustomersByStore(storeId);
            logger.info("Successfully retrieved customers for store with ID: {}", storeId);
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.error("No customers found for store with ID {}: {}", storeId, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
