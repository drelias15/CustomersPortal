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
    public String listCourse(Model model){
        model.addAttribute("customers", customerRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String courseForm(Model model){
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
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("customer", customerRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("customer", customerRepository.findById(id).get());
        return "customerform";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        customerRepository.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/search")
    public String searchCustomer(@Valid Customer customer, String lastName, Model model, BindingResult result){
        if (!result.hasErrors()) {
            model.addAttribute("customers", customerRepository.findByLastName(lastName));
            return "list";
        }
        model.addAttribute("customers", customerRepository.findByLastName(lastName));
        return "list";
    }

}