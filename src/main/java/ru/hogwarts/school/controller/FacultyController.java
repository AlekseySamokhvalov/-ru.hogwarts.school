package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return facultyService.add(faculty);
    }

    @PutMapping
    public Faculty update(@RequestBody Faculty faculty) {
        return facultyService.update(faculty);
    }

    @DeleteMapping("{id}")
    public Faculty delete(@RequestParam Long id) {
        return facultyService.delete(id);
    }

    @GetMapping("{id}")
    public Faculty get(@RequestParam Long id) {
        return facultyService.get(id);
    }
    @GetMapping("/by-color")
    public List<Faculty> getByColor(@RequestParam String color) {
        return facultyService.getByColor(color);
    }

    @GetMapping("/by-color-or-name")
    public List<Faculty> getByColorOrName(@RequestParam String param) {
        return facultyService.getByColorOrName(param);
    }

    @GetMapping("/students-by-id")
    public List<Student> getStudentsById(@RequestParam long id) {
        return facultyService.getStudents(id);
    }

}