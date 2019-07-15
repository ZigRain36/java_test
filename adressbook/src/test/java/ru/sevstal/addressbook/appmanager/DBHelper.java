package ru.sevstal.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.sevstal.addressbook.model.ContactListData;
import ru.sevstal.addressbook.model.Contacts;
import ru.sevstal.addressbook.model.GroupData;
import ru.sevstal.addressbook.model.Groups;

import java.util.List;

public class DBHelper {

    private final SessionFactory sessionFactory;

    public DBHelper() {


        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from ru.sevstal.addressbook.model.GroupData")
                .list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from ru.sevstal.addressbook.model.ContactListData where deprecated = '0000-00-00'")
                .list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

}
