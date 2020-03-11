package system.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import system.model.Student;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        Session session;

        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }


    public List<Student> getStudents() {
        List<Student> list = null;
        Session currentSession = null;
        try {
            currentSession = getCurrentSession();
            list =  currentSession.createQuery("from Student").list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (currentSession != null) {
                currentSession.close();
            }
        }

        return list;
    }

    public void addStudent(Student student) {
        Session currentSession = null;
        try {
            currentSession = getCurrentSession();
            currentSession.save(student);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (currentSession != null) {
                currentSession.close();
            }
        }
    }

    public void updateStudent(Student student) {
        Session currentSession = null;
        Transaction transaction = null;
        try {
            currentSession = getCurrentSession();
            transaction = currentSession.getTransaction();
            transaction.begin();
            Student studentForUpdate = currentSession.get(Student.class, student.getId());
            if (studentForUpdate != null) {
                studentForUpdate.setAge(student.getAge());
                studentForUpdate.setBirthDate(student.getBirthDate());
                studentForUpdate.setFaculty(student.getFaculty());
                studentForUpdate.setFirstName(student.getFirstName());
                studentForUpdate.setSecondName(student.getSecondName());
                currentSession.update(studentForUpdate);
            }
            transaction.commit();

        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            if (currentSession != null) {
                currentSession.close();
            }
        }
    }

    public Student getStudent(int id) {
        Student student = (Student)getCurrentSession().get(Student.class, id);
        return student;
    }
    @Transactional
    public void deleteStudent(int id) {
        System.out.println("in dao");
        Session currentSession = null;
        Transaction transaction = null;
        try {
            currentSession = getCurrentSession();
            transaction = currentSession.getTransaction();
            transaction.begin();
            Student studentForDelete = currentSession.get(Student.class, id);
            if (studentForDelete != null) {
                currentSession.remove(studentForDelete);
            }
            transaction.commit();

        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            if (currentSession != null) {
                currentSession.close();
            }
        }
        System.out.println("after dao");
    }
}
