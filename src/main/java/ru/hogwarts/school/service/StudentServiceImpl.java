package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private static long ID_COUNTER = 1;

    private final Map<Long, Student> studentById = new HashMap<>();

    public Student add(Student student) {
        Student newStudent = new Student(ID_COUNTER, student.getName(), student.getAge());
        ID_COUNTER++;
        studentById.put(newStudent.getId(), newStudent);
        return newStudent;
    }

    public Student update(Student student) {
        Student studentForUpdate = studentById.get(student.getId());
        if (studentForUpdate == null) {
            throw new StudentNotFoundException();
        }

        studentForUpdate.setName(student.getName());
        studentForUpdate.setAge(student.getAge());
        return studentForUpdate;
    }

    public Student delete(Long id) {
        Student studentForDelete = studentById.get(id);
        if (studentForDelete == null) {
            throw new StudentNotFoundException();
        }

        return studentById.remove(id);
    }

    public Student get(Long id) {
        Student studentForSearch = studentById.get(id);
        if (studentForSearch == null) {
            throw new StudentNotFoundException();
        }

        return studentForSearch;
    }

    @Override
    public List<Student> getByAge(int age) {
        return studentById.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }

    public Map<Long, Student> getAll() {
        return studentById;
    }
}