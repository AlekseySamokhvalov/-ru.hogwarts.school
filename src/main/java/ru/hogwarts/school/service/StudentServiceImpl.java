package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository repository) {
        this.studentRepository = repository;
    }

    public Student add(String name, int age) {
        Student newStudent = new Student(name, age);
        newStudent = studentRepository.save(newStudent);
        return newStudent;
    }
//    public Student add(Student student) { return repository.save(student); // добавили
//}
    @Override
    public Student update(long id, String name, Integer age) {
        Student studentForUpdate = get(id);
        studentForUpdate.setName(name);
        studentForUpdate.setAge(age);
        return studentRepository.save(studentForUpdate);
    }
    @Override
    public Student delete(Long id) {
        Student studentForDelete = get(id);
        studentRepository.deleteById(id);
        return studentForDelete;
    }
    @Override
    public Student get(Long id) {
        return studentRepository.findById(id).orElse(null); // вывели
    }

    @Override
    public List<Student> getByAge(int age) {
        return studentRepository.findAllByAge(age);
    }

    @Override
    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }
    @Override
    public List<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Faculty getFaculty(Long studentId) {
        return studentRepository.findById(studentId).get().getFaculty();
    }

    @Override
    public List<Student> findByFacultyId(long facultyId) {
        return studentRepository.findByFacultyId(facultyId);
    }
    @Override
    public Integer getCount(){
        return studentRepository.getCount();
    }
    @Override
    public Double getAvgAge(){
        return studentRepository.getAvgAge();
    }
    @Override
    public List<Student> getLastFiveStudent(){
        return studentRepository.getLastFiveStudent();
    }


}