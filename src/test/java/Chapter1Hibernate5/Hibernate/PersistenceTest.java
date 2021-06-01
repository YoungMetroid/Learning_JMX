package Chapter1Hibernate5.Hibernate;

import com.LearningHibernate.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class PersistenceTest {

    private SessionFactory factory = null;

    @BeforeClass
    public  void setUp()
    {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void saveMessage() {
        Message message = new Message("Hello, world");
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(message);
            tx.commit();
        }
    }

    @Test(dependsOnMethods = "saveMessage")
    public void readMessage(){
        try(Session session = factory.openSession()){
            List<Message> list = session.createQuery("from Message", Message.class).list();

            assertEquals(list.size(),1);
            for(Message m: list)
            {
                System.out.println(m);
            }
        }
    }


    public void CreateDataBase()
    {
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","ghost"))
        {

        }
        catch (SQLException ex)
        {

        }
    }

}
