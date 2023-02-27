package lk.ijse.computerShop.repository;

import lk.ijse.computerShop.to.Customer;
import lk.ijse.computerShop.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerRepository {
    private final Session session;

    public CustomerRepository(){
        session= SessionFactoryConfiguration.getInstance().getSession();
    }

    public String saveCustomer(Customer customer){
        Transaction transaction=session.beginTransaction();
        String id=" ";
        try {
            id= (String)(session.save(customer));
            transaction.commit();
            session.close();
            return id;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return id;
        }
    }
}
