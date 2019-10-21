package ee.praktika.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ee.praktika.springdemo.entity.Customer;
import ee.praktika.springdemo.service.CustomerService;

@Controller
@RequestMapping( "/customer" )
public class CustomerController {

    //need to inject the Customer Service
    @Autowired
    private CustomerService customerService;

    @GetMapping( "/list" )
    public String listCustomers( Model theModel ){

        //get customers from the Service
        List<Customer> theCustomers = customerService.getCustomers();

        //add those Customers to the Spring MVC model
        theModel.addAttribute( "customers", theCustomers );

        return "list-customers";
    }

    @GetMapping( "/showFormForAdd" )
    public String showFormForAdd( Model theModel ){

        //create a nwe model attribute to bind form data
        Customer theCustomer = new Customer();

        theModel.addAttribute( "customer", theCustomer );

        return "customer-form";
    }

    @PostMapping( "/saveCustomer" )
    public String saveCustomer( @ModelAttribute( "customer" ) Customer theCustomer ){

        //save the customer using our service
        customerService.saveCustomer( theCustomer );

        return "redirect:/customer/list";
    }

    @GetMapping( "/showFormForUpdate" )
    public String showFormForUpdate( @RequestParam( "customerId" ) int theId, Model theModel ){

        //get the Customer from Service
        Customer theCustomer = customerService.getCustomer( theId );

        //Set the Customer as a Model attribute to pre-populate the form
        theModel.addAttribute( "customer", theCustomer );

        //send data over to the form
        return "customer-form";
    }

    @GetMapping( "/delete" )
    public String deleteCustomer( @RequestParam( "customerId" ) int theId ){

        //delete the Customer
        customerService.deleteCustomer( theId );
        return "redirect:/customer/list";
    }

    @GetMapping( "/search" )
    public String searchCustomers( @RequestParam( "theSearchName" ) String theSearchName,
        Model theModel ){

        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers( theSearchName );

        // add the customers to the model
        theModel.addAttribute( "customers", theCustomers );

        return "list-customers";
    }

}
