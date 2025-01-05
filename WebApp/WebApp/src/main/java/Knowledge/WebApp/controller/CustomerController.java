package Knowledge.WebApp.controller;

import Knowledge.WebApp.dto.CustomerDTO;
import Knowledge.WebApp.model.Customer;
import Knowledge.WebApp.service.ICustomerService;
import Knowledge.WebApp.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> insertCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            Customer customer = customerService.insertCustomer(customerDTO);
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error inserting customer: {}" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomers (@RequestBody CustomerDTO customerDTO) {
        try {
            Customer customer = customerService.updateCustomer(customerDTO);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println("Error updating customer: {}" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        try {
            Customer customer = customerService.deleteCustomer(id);
            return new ResponseEntity<>(customer, HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            System.out.println("Error deleting customer: {}" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println("Customer with ID {} not found" + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vat/{vatNumber}")
    public ResponseEntity<Customer> getCustomerByVatNumber(@PathVariable String vatNumber) {
        try {
            Customer customer = customerService.getCustomerByVatNumber(vatNumber);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println("Customer with VAT number {} not found" + vatNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Customer>> getCustomersByStore(@PathVariable Long storeId) {
        try {
            List<Customer> customers = customerService.getCustomersByStore(storeId);
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println("No customers found for store with ID {}" + storeId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
