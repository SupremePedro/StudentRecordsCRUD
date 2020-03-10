package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.StudentDao;
import system.model.Student;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentDao studentDao;

    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    public Student getStudent(int id) {
        return studentDao.getStudent(id);
    }

    public void deleteStudent(int id) {
        studentDao.deleteStudent(id);
    }

    public List<Student> getStudents() {
        return studentDao.getStudents();
    }
}
