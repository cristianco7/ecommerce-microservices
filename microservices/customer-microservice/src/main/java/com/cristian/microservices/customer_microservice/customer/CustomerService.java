package com.cristian.microservices.customer_microservice.customer;

import com.cristian.microservices.customer_microservice.exceptions.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customerToCreate = mapper.toCustomer(request);
        customerToCreate.setId(null);
        var customer = repository.save(customerToCreate);
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        if (request.id() == null || request.id().isBlank()) {
            throw new IllegalArgumentException("Customer id is required for update");
        }

        Customer customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with id %s not found", request.id())));

        customer.setAddress(request.address());
        customer.setCity(request.city());
        customer.setEmail(request.email());
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setPhone(request.phone());

        repository.save(customer);
    }

    public CustomerResponse getCustomerById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::toCustomerResponse)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with id %s not found", customerId)));
    }

    public List<CustomerResponse> getCustomers() {
        return repository.findAll().stream()
                .map(mapper::toCustomerResponse)
                .toList();
    }


    public void deleteCustomerById(String customerId) {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("Customer id is required for delete");
        }

        Customer customer =repository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with id %s not found", customerId)));
        repository.delete(customer);
    }
}