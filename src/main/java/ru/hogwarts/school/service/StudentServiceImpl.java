package ru.hogwarts.school.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    public Student add(String name, int age) {
        Student newStudent = new Student(name, age);
        newStudent = repository.save(newStudent);
        return newStudent;
    }
//    public Student add(Student student) { return repository.save(student); // добавили
//}
    @Override
    public Student update(long id, String name, Integer age) {
        Student studentForUpdate = get(id);
        studentForUpdate.setName(name);
        studentForUpdate.setAge(age);
        return repository.save(studentForUpdate);
    }
    @Override
    public Student delete(Long id) {
        Student studentForDelete = get(id);
        repository.deleteById(id);
        return studentForDelete;
    }
    @Override
    public Student get(Long id) {
        return repository.findById(id).orElse(null); // вывели
    }

    @Override
    public List<Student> getByAge(int age) {
        return null;
    }

    @Override
    public Collection<Student> getAll() {
        return repository.findAll();
    }


}