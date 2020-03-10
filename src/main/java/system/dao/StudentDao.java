package system.dao;

import system.model.Student;

import java.util.List;

public interface StudentDao {
    public void addStudent(Student student);
    public void updateStudent(Student student);
    public Student getStudent(int id);
    public void deleteStudent(int id);
    public List<Student> getStudents();
}
