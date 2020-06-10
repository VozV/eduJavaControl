package service.mock;

import ru.edu.entity.Student;
import ru.edu.service.CrudService;

import java.util.ArrayList;
import java.util.List;

public class MockStudentService implements CrudService<Student> {
    @Override
    public List<Student> findAll() {
        return new ArrayList<>();
    }

    @Override
    public Student findById(Object id) {
        return new Student(String.valueOf(id), "testCategory");
    }

    @Override
    public Student create(Student student) {
        return student;
    }

    @Override
    public Student update(Student student) {
        return student;
    }

    @Override
    public void delete(Object id) {

    }
}
