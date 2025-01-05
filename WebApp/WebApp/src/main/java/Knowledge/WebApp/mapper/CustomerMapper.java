package Knowledge.WebApp.mapper;

import Knowledge.WebApp.dto.CustomerDTO;
import Knowledge.WebApp.model.Customer;
import Knowledge.WebApp.model.Store;

public class CustomerMapper {

    private CustomerMapper() {
    }

    public static CustomerDTO CustomerToDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setVatNumber(customer.getVatNumber());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setStoreId(customer.getStore().getId());
        return dto;
    }

    public static Customer CustomerToEntity(CustomerDTO dto, Store store) {
        if (dto == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setVatNumber(dto.getVatNumber());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setStore(store);
        return customer;
    }
}
