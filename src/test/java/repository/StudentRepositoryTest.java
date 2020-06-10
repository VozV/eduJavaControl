package repository;

import config.TestConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.edu.entity.Student;
import ru.edu.jpa.StudentRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Before
    public void addStudentTest() {
        Student student = new Student("Vasa", "123666666");
        studentRepository.save(student);
        Assert.assertNotNull(studentRepository.findOne(4));
    }

    @Test
    public void findAllStudentsTest() {
        List<Student> students = studentRepository.findAll();
        Assert.assertEquals(students.size(), 5);
    }

    @After
    public void delStudentTest() {
        studentRepository.delete(4);
        Assert.assertNull(studentRepository.findOne(4));
    }
}
