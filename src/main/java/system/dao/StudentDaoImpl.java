package system.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import system.model.Student;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
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
    }

    public Student getStudent(int id) {
        Student student = (Student)getCurrentSession().get(Student.class, id);
        return student;
    }

    public void deleteStudent(int id) {
        Student student = getStudent(id);
        if (student != null)
            getCurrentSession().delete(student);
    }
}
