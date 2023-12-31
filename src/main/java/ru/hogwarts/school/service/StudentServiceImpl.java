package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);
    public StudentServiceImpl(StudentRepository repository) {
        this.studentRepository = repository;
    }

    public Student add(String name, int age) {
        logger.info("Был вызван метод add");
        Student newStudent = new Student(name, age);
        newStudent = studentRepository.save(newStudent);
        return newStudent;
    }
//    public Student add(Student student) { return repository.save(student); // добавили
//}
    @Override
    public Student update(long id, String name, Integer age) {
        logger.info("Был вызван метод update");
        Student studentForUpdate = get(id);
        studentForUpdate.setName(name);
        studentForUpdate.setAge(age);
        return studentRepository.save(studentForUpdate);
    }
    @Override
    public Student delete(Long id) {
        logger.info("Был вызван метод delete");
        Student studentForDelete = get(id);
        studentRepository.deleteById(id);
        return studentForDelete;
    }
    @Override
    public Student get(Long id) {
        logger.info("Был вызван метод get");
        return studentRepository.findById(id).orElse(null); // вывели
    }

    @Override
    public List<Student> getByAge(int age) {
        logger.info("Был вызван метод getByAge");
        return studentRepository.findAllByAge(age);
    }

    @Override
    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }
    @Override
    public List<Student> findByAgeBetween(int min, int max) {
        logger.info("Был вызван метод findByAgeBetween");
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Faculty getFaculty(Long studentId) {
        logger.info("Был вызван метод getFaculty");
        return studentRepository.findById(studentId).get().getFaculty();
    }

    @Override
    public List<Student> findByFacultyId(long facultyId) {
        logger.info("Был вызван метод findByFacultyId");
        return studentRepository.findByFacultyId(facultyId);
    }
    @Override
    public Integer getCount(){
        logger.info("Был вызван метод getCount");
        return studentRepository.getCount();
    }
    @Override
    public Double getAvgAge(){
        logger.info("Был вызван метод getAvgAge");
        return studentRepository.getAvgAge();
    }
    @Override
    public List<Student> getLastFiveStudent(){
        logger.info("Был вызван метод getLastFiveStudent");
        return studentRepository.getLastFiveStudent();
    }

    @Override
    public List<String> getAllWhereNameStartWithA() {
        String startSymbol = "A";
        return studentRepository.findAll().stream()
                .map(student -> student.getName().toUpperCase())
                .filter(name -> name.startsWith(startSymbol.toUpperCase()))
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public double getAvgAgeWithStream() {
        return studentRepository.findAll().stream()
                .mapToDouble(student -> (double) student.getAge())
                .average()
                .orElse(0);
    }

}