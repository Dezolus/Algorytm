package aston.lesson03.config;

import aston.lesson03.model.Coursework;
import aston.lesson03.model.Lecturer;
import aston.lesson03.model.Person;
import aston.lesson03.model.Student;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;




public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Person.class)
                    .addAnnotatedClass(Lecturer.class)
                    .addAnnotatedClass(Coursework.class)
                    .configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void close() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}