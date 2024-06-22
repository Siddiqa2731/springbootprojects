package com.example.demo.service;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dto.Customer;
import com.example.demo.dto.UpdatePassword;
import com.example.demo.exception.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
    private final CustomerRepository repository;

    @Autowired
    public Service(CustomerRepository repository) {
        this.repository = repository;
    }

    /*
     * Retrieves details of a customer based on customer ID.
     *
     * @param custId The ID of the customer to retrieve.
     * @return ResponseEntity containing the customer details if found, or a 404 response if not found.
     */
    public ResponseEntity<?> getCustomerDetails(@PathVariable Integer custId) {
        try {
            Optional<Customer> customer = repository.findById(custId);
            if (customer.isEmpty()) {
                throw new CustomerException("Cannot find customer with customer Id " + custId);
            }
            return ResponseEntity.ok(customer);
        } catch (CustomerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /*
     * Retrieves details of a customer based on customer Name.
     *
     * @param customerName The Name of the customer to retrieve.
     * @return ResponseEntity containing the customer details if found, or a 404 response if not found.
     */
    public ResponseEntity<?> getCustomerDetailsByName(String customerName) {
        try {
            Customer customer = repository.findByCustomerName(customerName.trim());
            if (customer == null) {
                throw new CustomerException("Cannot find customer with customer Name " + customerName);
            }
            return ResponseEntity.ok(customer);
        } catch (CustomerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /*
     * Retrieves details of all customers.
     *
     * @return ResponseEntity containing the List of customers details.
     */
    public ResponseEntity<List<Customer>> getAllCustomer() {
        List<Customer> allCustomers = repository.findAll();
        return ResponseEntity.ok(allCustomers);
    }

    /*
     * Creates a new customer with the provided details.
     *
     * @param customer The customer object containing details to be inserted.
     * @return ResponseEntity with status 201 (Created) and success message if insertion is successful,
     *         or ResponseEntity with status 400 (Bad Request) and error message if validation fails or customer already exists.
     */
    public ResponseEntity<String> createCustomerDetails(Customer customer) {
        try {
            if (customer.getCustomerName() == null) {
                throw new CustomerException("Customer Name cannot be blank");
            }
            if (customer.getPassword() == null || customer.getConfirmPassword() == null) {
                throw new CustomerException("Password or confirm password cannot be null");
            } else if (!customer.getConfirmPassword().equals(customer.getPassword())) {
                throw new CustomerException("Password and confirm Password does not match");
            }
            Customer existingCustomer = repository.findByCustomerName(customer.getCustomerName().trim());
            if (existingCustomer != null) {
                throw new CustomerException("Customer with customer name " + customer.getCustomerName() + " already exists");
            }
            repository.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Customer inserted successfully");
        } catch (CustomerException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /*
     * Updates the password details for the customer identified by custid.
     *
     * @param updatePassword The object containing the updated password and confirm password.
     * @param custid The ID of the customer whose password is to be updated.
     * @return ResponseEntity with status 200 (OK) and success message if update is successful,
     *         or ResponseEntity with status 400 (Bad Request) and error message if validation fails or customer not found.
     */
    public ResponseEntity<String> updateCustomerDetails(UpdatePassword updatePassword, Integer custid) {
        try {
            if (updatePassword.getPassword() == null || updatePassword.getConfirmPassword() == null) {
                throw new CustomerException("Password or confirm password cannot be null");
            } else if (!updatePassword.getConfirmPassword().equals(updatePassword.getPassword())) {
                throw new CustomerException("Password and confirm Password does not match");
            }
            Optional<Customer> customer = repository.findById(custid);
            if (customer.isEmpty()) {
                throw new CustomerException("Customer with the given id not found");
            }
            Customer customerToBeUpdated = customer.get();
            customerToBeUpdated.setPassword(updatePassword.getPassword());
            customerToBeUpdated.setConfirmPassword(updatePassword.getConfirmPassword());
            repository.save(customerToBeUpdated);
            return ResponseEntity.ok("Password updated successfully");
        } catch (CustomerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /*
     * Deletes the customer identified by custId.
     *
     * @param custId The ID of the customer to be deleted.
     * @return ResponseEntity with status 200 (OK) and success message if deletion is successful,
     *         or ResponseEntity with status 404 (Not Found) and error message if customer not found.
     */
    public ResponseEntity<String> deleteCustomer(Integer custId) {
        try {
            Optional<Customer> customer = repository.findById(custId);
            if (customer.isEmpty()) {
                throw new CustomerException("Customer with the given id not found");
            }
            repository.deleteById(custId);
            return ResponseEntity.ok("Customer deleted successfully");
        } catch (CustomerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No customer with id: " + custId);
        }
    }
}
