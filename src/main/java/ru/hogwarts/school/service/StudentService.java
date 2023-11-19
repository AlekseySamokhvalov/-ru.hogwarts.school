package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    Student add(Student student);

    Student update(Student student);

    Student delete(Long id);

    Student get(Long id);

    List<Student> getByAge(int age);

    Map<Long, Student> getAll();
}