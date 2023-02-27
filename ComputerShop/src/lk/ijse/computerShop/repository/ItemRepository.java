package lk.ijse.computerShop.repository;

import lk.ijse.computerShop.to.Item;
import lk.ijse.computerShop.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ItemRepository {
    private final Session session;

    public ItemRepository(){
        session= SessionFactoryConfiguration.getInstance().getSession();
    }

    public Long saveItem(Item item){
        Transaction transaction=session.beginTransaction();

        try {
            Long id=(Long) session.save(item);
            transaction.commit();
            session.close();
            return id;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return -1L;
        }
    }
}
