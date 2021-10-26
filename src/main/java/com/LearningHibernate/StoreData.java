package com.LearningHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.net.URL;

public class StoreData {

    public static void main(String[] args)
    {
        URL resource = StoreData.class.getClassLoader().getResource("hibernate.cfg.xml");
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure(resource).build();
        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();

        SessionFactory factory = metadata.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee1 = new Employee();


        employee1.setId(3);
        employee1.setFirstName("Mankey3");
        employee1.setLastName("POG");

        session.save(employee1);
        transaction.commit();
        System.out.println("Successfully saved");
        factory.close();
        session.close();
    }
    public static String f()
    {
        return "";
    }
}
