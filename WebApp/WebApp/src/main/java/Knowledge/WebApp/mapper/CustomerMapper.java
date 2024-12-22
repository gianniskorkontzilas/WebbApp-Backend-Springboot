package Knowledge.WebApp.mapper;

import Knowledge.WebApp.dto.CustomerDTO;
import Knowledge.WebApp.model.Customer;

public class CustomerMapper {

    private CustomerMapper() {
    }

    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setVatNumber(customer.getVatNumber());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        return dto;
    }

    public static Customer toEntity(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setVatNumber(dto.getVatNumber());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        return customer;
    }
}
