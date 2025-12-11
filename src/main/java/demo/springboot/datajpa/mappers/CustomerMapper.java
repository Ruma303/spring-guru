package demo.springboot.datajpa.mappers;

import org.mapstruct.Mapper;

import demo.springboot.datajpa.dto.CustomerDTO;
import demo.springboot.datajpa.model.Customer;

@Mapper
public interface CustomerMapper {

    Customer customerToCustomerDto(Customer dto);

    CustomerDTO customerDtoToCustomer(CustomerDTO customer);
}
