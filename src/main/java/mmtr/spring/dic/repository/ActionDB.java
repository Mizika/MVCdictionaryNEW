package mmtr.spring.dic.repository;

import mmtr.spring.dic.model.FirstDictionary;
import mmtr.spring.dic.model.SecondDictionary;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.List;

@Repository
public class ActionDB implements IAllActionDB {
    private Query query;

    public static SessionFactory connectDB() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        return sessionFactory;
    }

    private static List<FirstDictionary> FirstDic(){
        List<FirstDictionary> data;
        Session session = connectDB().openSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<FirstDictionary> query = builder.createQuery(FirstDictionary.class);
        Root<FirstDictionary> root = query.from(FirstDictionary.class);
        query.select(root);
        org.hibernate.query.Query<FirstDictionary> q = session.createQuery(query);
        data = q.getResultList();
        session.getTransaction().commit();
        session.close();
        return data;
    }

    private static List<SecondDictionary> SecondDic(){
        List<SecondDictionary> data;
        Session session = connectDB().openSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SecondDictionary> query = builder.createQuery(SecondDictionary.class);
        Root<SecondDictionary> root = query.from(SecondDictionary.class);
        query.select(root);
        org.hibernate.query.Query<SecondDictionary> q = session.createQuery(query);
        data = q.getResultList();
        session.getTransaction().commit();
        session.close();
        return data;
    }


    @Override
    public List<?> showAllFromDic(String dicName) throws Exception {
        if (dicName.equals("first.txt")){
            return FirstDic();
        } else if (dicName.equals("second.txt")) {
            return SecondDic();
        } else {
            throw new Exception("Something bad happened.");
        }

    }

    @Override
    public String searchByKey(String dicName, String key) throws IOException {
        try{
        Session session = connectDB().openSession();
        session.beginTransaction();
        if (dicName.equals("first.txt")){
            query = session.createQuery("SELECT value FROM FirstDictionary a WHERE a.key = :key").setParameter("key", key);
        } else if (dicName.equals("second.txt")) {
            query = session.createQuery("SELECT value FROM SecondDictionary a WHERE a.key = :key").setParameter("key", key);
        }
        String resultQuery = query.getSingleResult().toString();
        session.getTransaction().commit();
        session.close();
        return resultQuery;
        } catch(NoResultException e) {
            return "Значение с данным ключом не существует!";
        }
    }

    @Override
    public String removeFromFile(String dicName, String key) throws IOException {
        try{
            Session session = connectDB().openSession();
            session.beginTransaction();
            if (dicName.equals("first.txt")){
                query = session.createQuery("delete FROM FirstDictionary a where a.key = :key").setParameter("key", key);
            } else if (dicName.equals("second.txt")) {
                query = session.createQuery("delete FROM SecondDictionary a where a.key = :key").setParameter("key", key);
            }
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            return "Значение было удалено!";
        } catch(NoResultException e) {
            return "Значение с данным ключом не существует!";
        }
    }

    @Override
    public String addValue(String dicName, String key, String value) throws IOException {
        try{
            Session session = connectDB().openSession();
            session.beginTransaction();
            if (dicName.equals("first.txt")){
                session.save(new FirstDictionary(key, value));
            } else if (dicName.equals("second.txt")) {
                session.save(new SecondDictionary(key, value));
            }
            session.getTransaction().commit();
            session.close();
            return "Значение было добавлено!";
        } catch(Exception e) {
            return "Введено не корректное значение!";
        }

    }

    @Override
    public String generateData() {
        GenerationDataDB.generateFirstTable();
        GenerationDataDB.generateSecondTable();
        return null;
    }
}