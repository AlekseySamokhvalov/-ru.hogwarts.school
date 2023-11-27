package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

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
        return repository.findAllByAge(age);
    }

    @Override
    public Collection<Student> getAll() {
        return repository.findAll();
    }
    @Override
    public List<Student> findByAgeBetween(int min, int max) {
        return repository.findByAgeBetween(min, max);
    }

    @Override
    public Faculty getFaculty(Long studentId) {
        return repository.findById(studentId).get().getFaculty();
    }

    @Override
    public List<Student> findByFacultyId(long facultyId) {
        return repository.findByFacultyId(facultyId);
    }


}