package aston.lesson03.DAO;

import aston.lesson03.config.HibernateUtil;
import aston.lesson03.model.Lecturer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LecturerDAO {

    public void addLecturer(Lecturer lecturer) {
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(lecturer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public Lecturer getLecturer(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Lecturer.class, id);
        }
    }

    public void updateLecturer(Lecturer lecturer) {
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(lecturer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

    }
    public void deleteLecturer(int id) {
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Lecturer lecturer = session.get(Lecturer.class, id);
            session.delete(lecturer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

    }

    public List<Lecturer> getAllLecturers() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Lecturer", Lecturer.class).getResultList();
        }
    }
}
