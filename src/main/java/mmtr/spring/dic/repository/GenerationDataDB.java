package mmtr.spring.dic.repository;

import com.github.javafaker.Faker;
import mmtr.spring.dic.model.FirstDictionary;
import mmtr.spring.dic.model.SecondDictionary;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerationDataDB {
    public static SessionFactory connectDB() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        return sessionFactory; }

    public static void generateFirstTable() {
        Session session = connectDB().openSession();
        session.beginTransaction();
        Random rand = new Random();
        Faker faker = new Faker();
        Pattern letters = Pattern.compile("\\D{4}");
        Matcher fileReg;
        for (int i = 0; i < 5;) {
            String firstName = faker.name().firstName();
            fileReg = letters.matcher(firstName);
            if (fileReg.matches()) {
                int randN = rand.nextInt(10000);
                String randNub = String.valueOf(randN);
                session.save(new FirstDictionary(randNub, firstName));
                i++;
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    public static void generateSecondTable() {
        Session session = connectDB().openSession();
        session.beginTransaction();
        Random rand = new Random();
        Faker faker = new Faker();
        Pattern number = Pattern.compile("\\d{5}");
        Pattern letters = Pattern.compile("\\D{2}");
        Matcher fileReg;
        for (int i = 0; i < 5;) {
            int randN = rand.nextInt(1000000);
            String randNub = String.valueOf(randN);
            fileReg = number.matcher(randNub);
            if (fileReg.matches()) {
                String firstName = faker.name().firstName();
                fileReg = letters.matcher(firstName);
                if (fileReg.matches()) {
                    session.save(new SecondDictionary(firstName, randNub));
                    i++;
                }
            }
        }
        session.getTransaction().commit();
        session.close();
    }


}
