package com.example.customers;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    ArrayList<Customer> findByLastName(String name);
}
