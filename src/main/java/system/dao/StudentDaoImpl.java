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
        return getCurrentSession().createQuery("from Student").list();
    }

    public void addStudent(Student student) {
        getCurrentSession().save(student);
    }

    public void updateStudent(Student student) {
        Student studentToUpdate = getStudent(student.getId());
        studentToUpdate.setFirstName(student.getFirstName());
        studentToUpdate.setSecondName(student.getSecondName());
        studentToUpdate.setAge(student.getAge());
        studentToUpdate.setBirthDate(student.getBirthDate());
        studentToUpdate.setFaculty(student.getFaculty());

        getCurrentSession().update(studentToUpdate);
        sessionFactory.close();
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
            }else{

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
