package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {

    Student add(String name, int age);

    Student update(long id, String name, Integer age);

    Student delete(Long id);

    Student get(Long id);

    List<Student> getByAge(int age);

    Collection<Student> getAll();

    List<Student> findByAgeBetween(int min, int max);

    Faculty getFaculty(Long studentId);

    List<Student> findByFacultyId(long facultyId);
}