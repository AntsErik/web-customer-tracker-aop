package ee.praktika.springdemo.DAO;

import java.util.List;

import ee.praktika.springdemo.entity.Customer;

public interface CustomerDAO {

    public List<Customer> getCustomers();

    public void saveCustomer( Customer theCustomer );

    public Customer getCustomer( int theId );

    public void deleteCustomer( int theId );

    public List<Customer> searchCustomers( String theSearchName );

}
