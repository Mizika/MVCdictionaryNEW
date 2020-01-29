package mmtr.spring.dic.repository;

import mmtr.spring.dic.model.FirstDictionary;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class testQuery {
    private static SessionFactory sessionFactory;

    public static SessionFactory connectDB() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        return sessionFactory;
    }

    public static void main(String[] args) {
        Session session = connectDB().openSession();
        session.beginTransaction();

        FirstDictionary get = session.load(FirstDictionary.class, 4);
        System.out.println(get.getValue());

        session.getTransaction().commit();
        session.close();
    }
}

