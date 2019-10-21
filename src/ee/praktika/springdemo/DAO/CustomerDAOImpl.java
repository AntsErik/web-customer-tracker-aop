package ee.praktika.springdemo.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ee.praktika.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    //need to inject the hibernate session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers(){

        //get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //create a query .. sort by last name
        Query<Customer> theQuery = currentSession.createQuery( "from Customer order by lastName",
            Customer.class );

        //and get the list of Customers from the query
        List<Customer> customers = theQuery.getResultList();

        //return the retrieved List of Customers
        return customers;
    }

    @Override
    public void saveCustomer( Customer theCustomer ){

        //get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //saving or updating the customer
        currentSession.saveOrUpdate( theCustomer );

    }

    @Override
    public Customer getCustomer( int theId ){

        //get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //retrieve/read the object from DB, using primary key (theId)
        Customer theCustomer = currentSession.get( Customer.class, theId );

        return theCustomer;
    }

    @Override
    public void deleteCustomer( int theId ){

        //get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //delete the retrieved Customer with the primary key (passed in)
        Query theQuery = currentSession.createQuery( "delete from Customer where id=:customerId" );
        theQuery.setParameter( "customerId", theId );

        //excecution
        theQuery.executeUpdate();

    }

    @Override
    public List<Customer> searchCustomers( String theSearchName ){
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = null;

        //
        // only search by name if theSearchName is not empty
        //
        if( theSearchName != null && theSearchName.trim().length() > 0 ) {

            // search for firstName or lastName ... case insensitive
            theQuery = currentSession.createQuery(
                "from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class );
            theQuery.setParameter( "theName", "%" + theSearchName.toLowerCase() + "%" );

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery = currentSession.createQuery( "from Customer", Customer.class );
        }

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results        
        return customers;

    }

}
