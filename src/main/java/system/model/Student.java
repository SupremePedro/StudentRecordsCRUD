package system.model;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name ="first_name", length = 80)
    private String firstName;

    @Column(name = "second_name", length = 80)
    private String secondName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "age")
    private Integer age;

    @Column(name = "faculty")
    private String faculty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Student() {
    }

    public Student(String firstName, String secondName, Date birthDate, Integer age, String faculty) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.age = age;
        this.faculty = faculty;
    }
}
