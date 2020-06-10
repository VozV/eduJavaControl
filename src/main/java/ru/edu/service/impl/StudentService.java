package ru.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.entity.Student;
import ru.edu.exсeptions.entity.EntityAlreadyExistsException;
import ru.edu.exсeptions.entity.EntityIllegalArgumentException;
import ru.edu.exсeptions.entity.EntityNotFoundException;
import ru.edu.jpa.StudentRepository;
import ru.edu.service.CrudService;
import ru.edu.service.Utillity;

import java.util.List;

@Service
public class StudentService implements CrudService<Student> {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Object id) {
        Student student = studentRepository.findOne(Utillity.parseId(id));
        if (student == null) {
            throw new EntityNotFoundException(Student.TYPE_NAME, id);
        }
        return student;
    }

    @Override
    public Student create(Student student) {
        checkStudent(student);
        if (studentRepository.findByPassport(student.getPassport()) != null) {
            throw new EntityAlreadyExistsException(Student.TYPE_NAME, student.getPassport());
        }
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student) {
        if (student.getId() == null) {
            throw new EntityIllegalArgumentException("Не указан ID студента");
        }
        checkStudent(student);
        if (studentRepository.findOne(student.getId()) == null) {
            throw new EntityNotFoundException(Student.TYPE_NAME, student.getId());
        }
        return studentRepository.save(student);
    }

    @Override
    public void delete(Object id) {
        Student student = findById(id);
        studentRepository.delete(student);
    }

    private void checkStudent(Student student) {
        if (student == null) {
            throw new EntityIllegalArgumentException("Объект не может быть null");
        }
        if (student.getPassport() == null) {
            throw new EntityIllegalArgumentException("Паспорт студента не может быть null");
        }
    }

}
