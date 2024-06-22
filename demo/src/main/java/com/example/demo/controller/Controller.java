package com.example.demo.controller;

import com.example.demo.dto.Customer;
import com.example.demo.dto.UpdatePassword;
import com.example.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class Controller {
    private final Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/id/{custId}")
    public ResponseEntity<?> getCustomerDetails(@PathVariable Integer custId) {
        return service.getCustomerDetails(custId);
    }

    @GetMapping("/name")
    public ResponseEntity<?> getCustomerDetailsByName(@RequestParam String custName) {
        return service.getCustomerDetailsByName(custName);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCustomers() {
        return service.getAllCustomer();
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        return service.createCustomerDetails(customer);
    }

    @PatchMapping("/updatePassword/{custId}")
    public ResponseEntity<String> UpdateCustomer(@RequestBody UpdatePassword updatePassword, @PathVariable Integer custId) {
        return service.updateCustomerDetails(updatePassword, custId);
    }

    @DeleteMapping("/deleteAccount/{custId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer custId) {
        return service.deleteCustomer(custId);
    }
}
