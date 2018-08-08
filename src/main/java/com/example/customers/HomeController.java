package com.example.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("/")
    public String listCustomers(Model model){
        model.addAttribute("customers", customerRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String customerForm(Model model){
        model.addAttribute("customer", new Customer());
        return "customerform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Customer customer, BindingResult result)
    {
        if (result.hasErrors()){
            return "customerform";
        }
        customerRepository.save(customer);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showCustomer(@PathVariable("id") long id, Model model){
        model.addAttribute("customer", customerRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") long id, Model model){
        model.addAttribute("customer", customerRepository.findById(id).get());
        return "customerform";
    }

    @RequestMapping("/delete/{id}")
    public String delCustomer(@PathVariable("id") long id){
        customerRepository.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/search")
    public String searchCustomer(@RequestParam("search") String search, @RequestParam("search2") String search2, Model model){
        model.addAttribute("customers", customerRepository.findByFirstNameAndLastName(search, search2));
        return "list";
    }

}