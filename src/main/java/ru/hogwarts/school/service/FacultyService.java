package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository repository;
    private StudentService studentService;

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty add(Faculty faculty) {
        logger.info("Был вызван метод add");
        return repository.save(faculty); // добавили
    }

    public Faculty update(Faculty faculty) {
        logger.info("Был вызван метод update");
        return repository.findById(faculty.getId())
                .map(entity -> repository.save(faculty))
                .orElse(null);
    }

    public Faculty delete(Long id) {
        logger.info("Был вызван метод delete");
        var entity = repository.findById(id).orElse(null);
        if (entity != null) {
            repository.delete(entity);
        }
        return entity; // удалили
    }

    public Faculty get(Long id) {
        logger.info("Был вызван метод get");
        return repository.findById(id).orElse(null); // вывели
    }
    public List<Faculty> getByColor(String color) {
        logger.info("Был вызван метод getByColor");
        return repository.findAllByColor(color);
    }

    public List<Faculty> getByColorOrName(String param) {
        logger.info("Был вызван метод getByColorOrName");
        return repository.findByColorContainsIgnoreCaseOrNameContainsIgnoreCase(param, param);
    }

    public List<Student> getStudents(Long id) {
        logger.info("Был вызван метод getStudents");
        return studentService.findByFacultyId(id);
    }

}
