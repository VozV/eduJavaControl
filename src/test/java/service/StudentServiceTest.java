package service;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.edu.entity.Student;
import ru.edu.exсeptions.entity.EntityAlreadyExistsException;
import ru.edu.exсeptions.entity.EntityHasDetailsException;
import ru.edu.exсeptions.entity.EntityIllegalArgumentException;
import ru.edu.exсeptions.entity.EntityNotFoundException;
import ru.edu.service.impl.StudentService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullStudentException() {
        studentService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullIdStudentException() {
        studentService.create(new Student());
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void createAlreadyExistsStudentException() {
        Student student = new Student("Vasya Pupkin", "123");
        studentService.create(student);
    }

    @Test
    public void createStudentSuccess() {
        Student student = new Student("Petr", "pass");
        Assert.assertEquals(studentService.create(student).getId(), student.getId());
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByIdNullException() {
        studentService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByIdNoNumericIdException() {
        studentService.findById("vv");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdNotFoundException() {
        studentService.findById(10);
    }

    @Test
    public void findByIdSuccess() {
        Student student = studentService.findById(2);
        Assert.assertEquals((int) student.getId(), 2);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteNullException() {
        studentService.delete(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteNoNumericIdException() {
        studentService.delete("vv");
    }

    @Test
    public void deleteSuccess() {
        studentService.delete(4);
    }
}
