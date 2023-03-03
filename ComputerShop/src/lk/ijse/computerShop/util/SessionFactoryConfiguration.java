package lk.ijse.computerShop.util;

import lk.ijse.computerShop.to.Customer;
import lk.ijse.computerShop.to.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class SessionFactoryConfiguration {


    private static SessionFactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfiguration(){
        Properties properties=new Properties();

//        hibernate.properties fill set
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));

        }catch (Exception e){
            e.printStackTrace();
        }
        Configuration configuration=new Configuration();
        configuration.addAnnotatedClass(Customer.class);
//        configuration.addAnnotatedClass(Item.class);
        sessionFactory=configuration.mergeProperties(properties).buildSessionFactory();






    }

    public static SessionFactoryConfiguration getInstance(){
        return null==factoryConfiguration ? factoryConfiguration=new SessionFactoryConfiguration() :factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
